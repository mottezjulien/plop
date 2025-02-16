package com.julien.plop.game.domain;

import com.julien.plop.ListTools;
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

        void save(Game.Id gameId, Board board);

    }

    private final DataOutput dataOutput;

    private final EventOutput eventOutput;

    private final GameVerifyUseCase verifyUseCase;

    public GameMoveUseCase(GameVerifyUseCase verifyUseCase, DataOutput dataOutput, EventOutput eventOutput) {
        this.dataOutput = dataOutput;
        this.eventOutput = eventOutput;
        this.verifyUseCase = verifyUseCase;
    }

    public void apply(Game.Id gameId, Player.Id playerId, BoardSpace.Point point) throws GameException {
        verifyUseCase.apply(gameId, playerId);

        //TODO NEEDS ??  dataOutput.trace(player, coordinate);
        Board board = dataOutput.findBoard(gameId).orElseThrow(() -> new GameException(GameException.Type.GAME_NOT_FOUND)); //TODO NEEDS ??
        List<BoardSpace> previousSpaces = board.is(playerId);
        board.move(playerId, point);
        List<BoardSpace> currentSpaces = board.is(playerId);

        List<BoardSpace> goInSpaces = ListTools.diffOthersInMore(previousSpaces, currentSpaces);
        goInSpaces.forEach(space -> {
            Event.Meta meta  = new Event.Meta();
            meta.put(Event.Meta.Key.PLAYER_ID, playerId);
            meta.put(Event.Meta.Key.SPACE_ID, space.id());
            eventOutput.fire(new Event(Event.Type.GO_IN_SPACE, meta));
        });

        List<BoardSpace> goOutSpaces = ListTools.diffOthersInMore(currentSpaces, previousSpaces);
        goOutSpaces.forEach(space -> {
            Event.Meta meta  = new Event.Meta();
            meta.put(Event.Meta.Key.PLAYER_ID, playerId);
            meta.put(Event.Meta.Key.SPACE_ID, space.id());
            eventOutput.fire(new Event(Event.Type.GO_OUT_SPACE, meta));
        });

        dataOutput.save(gameId, board);

    }

}
