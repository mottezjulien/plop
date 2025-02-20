package com.julien.plop.game.adapter;

import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.game.domain.GamePlayerUseCase;
import com.julien.plop.game.persistence.GamePlayerEntity;
import com.julien.plop.game.persistence.GamePlayerRepository;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.player.domain.model.Player;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GamePlayerDataAdapter implements GamePlayerUseCase.DataOutput {

    private final GameRepository gameRepository;

    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayerDataAdapter(GameRepository repository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
    }

    @Override
    public boolean isExist(Game.Id gameId) {
        return gameRepository.existsById(gameId.value());
    }

    @Override
    public boolean isStarted(Game.Id gameId) {
        return gameRepository.isStarted(gameId.value());
    }

    @Override
    public Optional<GamePlayer> findById(Game.Id gameId, Player.Id playerId) {
        GamePlayerEntity.GamePlayerId id = new GamePlayerEntity.GamePlayerId(gameId.value(), playerId.value());
        return gamePlayerRepository.findByIdFetchPlayer(id)
                .map(GamePlayerEntity::toModel);
    }

}
