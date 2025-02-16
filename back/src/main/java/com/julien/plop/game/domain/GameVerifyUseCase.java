package com.julien.plop.game.domain;

import com.julien.plop.player.domain.model.Player;

public class GameVerifyUseCase {

    public interface DataOutput {

        boolean isExist(Game.Id gameId);

        boolean isStarted(Game.Id gameId);

        boolean isIn(Game.Id gameId, Player.Id playerId);

    }

    private final DataOutput dataOutput;

    public GameVerifyUseCase(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    public void apply(Game.Id gameId, Player.Id playerId) throws GameException {
        if(!dataOutput.isExist(gameId)) {
                throw new GameException(GameException.Type.GAME_NOT_FOUND);
        }
        if(!dataOutput.isStarted(gameId)) {
            throw new GameException(GameException.Type.GAME_NOT_STARTED);
        }
        if(!dataOutput.isIn(gameId, playerId)) {
            throw new GameException(GameException.Type.PLAYER_NOT_IN);
        }
    }
}
