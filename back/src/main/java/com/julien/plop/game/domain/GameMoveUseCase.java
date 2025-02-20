package com.julien.plop.game.domain;

import com.julien.plop.tools.ListTools;
import com.julien.plop.board.model.Board;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.event.domain.Event;
import com.julien.plop.event.domain.EventOutput;
import com.julien.plop.player.domain.model.Player;

import java.util.List;
import java.util.Optional;

public class GameMoveUseCase {

    public interface DataOutput {

        Optional<Board> findBoard(Game.Id gameId);

        void savePosition(GamePlayer gamePlayer) throws GameException;

    }


    private final DataOutput dataOutput;
    private final GamePlayerUseCase gamePlayerUseCase;
    private final EventOutput eventOutput;


    public GameMoveUseCase(DataOutput dataOutput, GamePlayerUseCase gamePlayerUseCase, EventOutput eventOutput) {
        this.dataOutput = dataOutput;
        this.eventOutput = eventOutput;
        this.gamePlayerUseCase = gamePlayerUseCase;
    }

    public void apply(Game.Id gameId, Player.Id playerId, BoardSpace.Point point) throws GameException {

        GamePlayer inGamePlayer = gamePlayerUseCase.apply(gameId, playerId);

        if(inGamePlayer.position().map(p -> p.equals(point)).orElse(false)) {
            return;
        }

        //TODO NEEDS ??  dataOutput.trace(player, coordinate);
        Board board = dataOutput.findBoard(gameId).orElseThrow(() -> new GameException(GameException.Type.GAME_NOT_FOUND));

        List<BoardSpace> previousSpaces = board.spaces(inGamePlayer);
        inGamePlayer.move(point);
        List<BoardSpace> currentSpaces = board.spaces(inGamePlayer);

        List<BoardSpace> goOutSpaces = ListTools.diffOthersInMore(currentSpaces, previousSpaces);
        goOutSpaces.forEach(space -> {
            Event.Meta meta  = new Event.Meta();
            meta.put(Event.Meta.Key.PLAYER_ID, playerId);
            meta.put(Event.Meta.Key.SPACE_ID, space.id());
            eventOutput.fire(new Event(Event.Type.GO_OUT_SPACE, meta));
        });

        List<BoardSpace> goInSpaces = ListTools.diffOthersInMore(previousSpaces, currentSpaces);
        goInSpaces.forEach(space -> {
            Event.Meta meta  = new Event.Meta();
            meta.put(Event.Meta.Key.PLAYER_ID, playerId);
            meta.put(Event.Meta.Key.SPACE_ID, space.id());
            eventOutput.fire(new Event(Event.Type.GO_IN_SPACE, meta));
        });

        dataOutput.savePosition(inGamePlayer);
    }

}
