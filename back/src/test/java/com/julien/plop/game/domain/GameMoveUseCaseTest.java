package com.julien.plop.game.domain;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.event.domain.EventOutput;
import com.julien.plop.player.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameMoveUseCaseTest {

    private final GameVerifyUseCase verifyUseCase = mock(GameVerifyUseCase.class);
    private final GameMoveUseCase.DataOutput dataOutPort = mock(GameMoveUseCase.DataOutput.class);
    private final EventOutput eventOutput = mock(EventOutput.class);
    private final GameMoveUseCase useCase = new GameMoveUseCase(verifyUseCase, dataOutPort, eventOutput);

    private final Game.Id gameId = new Game.Id("gameId");
    private final Player.Id playerId = new Player.Id("playerId");

    @Test
    void throwThisExceptionIfVerifyThrowAnyException() throws GameException {
        GameException exception = new GameException(GameException.Type.GAME_NOT_FOUND);
        doThrow(exception)
                .when(verifyUseCase).apply(gameId, playerId);

        BoardSpace.Point point = new BoardSpace.Point(6.0789f, 80.6530f);
        assertThatThrownBy(() -> useCase.apply(gameId, playerId, point))
                .isInstanceOf(GameException.class)
                .isEqualTo(exception);
    }

    @Test
    void plop() throws GameException {
        doNothing().when(verifyUseCase).apply(gameId, playerId);

        next


    }


}