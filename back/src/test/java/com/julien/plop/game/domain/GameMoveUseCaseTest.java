package com.julien.plop.game.domain;

import com.julien.plop.board.model.Board;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.event.domain.Event;
import com.julien.plop.event.domain.EventOutput;
import com.julien.plop.player.domain.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameMoveUseCaseTest {

    private final ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);
    private final InMemoryData dataOutPort = new InMemoryData();
    private final GamePlayerUseCase gamePlayerUseCase = mock(GamePlayerUseCase.class);
    private final EventOutput eventOutput = mock(EventOutput.class);
    private final GameMoveUseCase useCase = new GameMoveUseCase(dataOutPort, gamePlayerUseCase, eventOutput);

    private final Game.Id gameId = new Game.Id("gameId");
    private final Player.Id playerId = new Player.Id("playerId");

    @BeforeEach
    void setUp() {
        dataOutPort.clear();
        BoardSpace spaceA = new BoardSpace(
                new BoardSpace.Id("spaceIdA"), "Space A", 1,
                List.of(new BoardSpace.Rect(new BoardSpace.Point(0.0f, 0.0f), new BoardSpace.Point(5.0f, 5.0f)))
        );
        BoardSpace spaceB = new BoardSpace(
                new BoardSpace.Id("spaceIdB"), "Space B", 1,
                List.of(new BoardSpace.Rect(new BoardSpace.Point(0.0f, 5.0f), new BoardSpace.Point(5.0f, 10.0f)))
        );
        List<BoardSpace> spaces = List.of(spaceA, spaceB);
        Board board = new Board(new Board.Id("BoardId"), spaces);
        dataOutPort.saveBoard(gameId, board);
    }

    @Test
    void throwThisExceptionIfVerifyThrowAnyException() throws GameException {
        GameException exception = new GameException(GameException.Type.GAME_NOT_FOUND);
        doThrow(exception)
                .when(gamePlayerUseCase).apply(gameId, playerId);

        BoardSpace.Point point = inSpaceA();
        assertThatThrownBy(() -> useCase.apply(gameId, playerId, point))
                .isInstanceOf(GameException.class)
                .isEqualTo(exception);
    }

    @Test
    void saveBoard() throws GameException {
        when(gamePlayerUseCase.apply(gameId, playerId))
                .thenReturn(new GamePlayer(gameId, new Player(playerId, "Bob"), Optional.empty()));

        useCase.apply(gameId, playerId, inSpaceA());
        assertThat(dataOutPort.findPlayerById(playerId))
                .satisfies(player -> assertThat(player.position()).isPresent().hasValue(inSpaceA()));
    }

    @Test
    void fireGoInAndGoOutEvents() throws GameException {
        when(gamePlayerUseCase.apply(gameId, playerId))
                .thenReturn(new GamePlayer(gameId, new Player(playerId, "Bob"), Optional.empty()));

        useCase.apply(gameId, playerId, inSpaceA());
        verify(eventOutput).fire(argumentCaptor.capture());
        assertEvent(argumentCaptor.getValue(), new BoardSpace.Id("spaceIdA"), Event.Type.GO_IN_SPACE);
        reset(eventOutput);

        useCase.apply(gameId, playerId, inSpaceB());
        verify(eventOutput, times(2)).fire(argumentCaptor.capture());
        List<Event> list = argumentCaptor.getAllValues();
        assertThat(list)
                .anySatisfy(anyEvent -> assertEvent(anyEvent, new BoardSpace.Id("spaceIdA"), Event.Type.GO_OUT_SPACE))
                .anySatisfy(anyEvent -> assertEvent(anyEvent, new BoardSpace.Id("spaceIdB"), Event.Type.GO_IN_SPACE));
        reset(eventOutput);

        useCase.apply(gameId, playerId, outSpace());
        verify(eventOutput).fire(argumentCaptor.capture());
        assertEvent(argumentCaptor.getValue(), new BoardSpace.Id("spaceIdB"), Event.Type.GO_OUT_SPACE);

    }

    private void assertEvent(Event event, BoardSpace.Id spaceId, Event.Type type) {
        assertThat(event.playerId()).isEqualTo(playerId);
        assertThat(event.spaceId()).isEqualTo(spaceId);
        assertThat(event.type()).isEqualTo(type);
    }

    private static BoardSpace.Point inSpaceA() {
        return new BoardSpace.Point(1.0789f, 4.6530f);
    }
    private static BoardSpace.Point inSpaceB() {
        return new BoardSpace.Point(4.2f, 7.4f);
    }
    private static BoardSpace.Point outSpace() {
        return new BoardSpace.Point(24.0f, -10f);
    }

    static class InMemoryData implements GameMoveUseCase.DataOutput {

        private final Map<Game.Id, Board> boards = new HashMap<>();
        private final List<GamePlayer> players = new ArrayList<>();

        public void clear() {
            boards.clear();
            players.clear();
        }

        @Override
        public Optional<Board> findBoard(Game.Id gameId) {
            return Optional.ofNullable(boards.get(gameId));
        }

        @Override
        public void savePosition(GamePlayer gamePlayer) {
            players.removeIf(player -> player.playerId().equals(gamePlayer.playerId()));
            players.add(gamePlayer);
        }

        public GamePlayer findPlayerById(Player.Id playerId) {
            return players.stream()
                    .filter(player -> player.playerId().equals(playerId))
                    .findFirst()
                    .orElseThrow();
        }

        public void saveBoard(Game.Id gameId, Board board) {
            boards.put(gameId, board);
        }

    }

}