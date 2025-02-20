package com.julien.plop.game.domain;

import com.julien.plop.player.domain.model.Player;

import java.util.Optional;

public class GamePlayerUseCase {

    public interface DataOutput {

        boolean isExist(Game.Id gameId);

        boolean isStarted(Game.Id gameId);

        Optional<GamePlayer> findById(Game.Id gameId, Player.Id playerId);
    }

    private final DataOutput dataOutput;

    public GamePlayerUseCase(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    public GamePlayer apply(Game.Id gameId, Player.Id playerId) throws GameException {
        if(!dataOutput.isExist(gameId)) {
                throw new GameException(GameException.Type.GAME_NOT_FOUND);
        }
        if(!dataOutput.isStarted(gameId)) {
            throw new GameException(GameException.Type.GAME_NOT_STARTED);
        }
        return dataOutput.findById(gameId, playerId)
                .orElseThrow(() -> new GameException(GameException.Type.PLAYER_NOT_IN_GAME));
    }
}
