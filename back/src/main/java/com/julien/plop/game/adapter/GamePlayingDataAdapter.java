package com.julien.plop.game.adapter;

import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.game.domain.usecase.GamePlayingUseCase;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GamePlayerEntity;
import com.julien.plop.game.persistence.GamePlayerRepository;
import com.julien.plop.game.persistence.GameRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GamePlayingDataAdapter implements GamePlayingUseCase.DataOutputPort {

    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;

    public GamePlayingDataAdapter(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
    }

    @Override
    public Optional<Game> findById(Game.Id gameId) {
        return gameRepository.findByIdFetchAll(gameId.value())
                .map(GameEntity::toModel);
    }

    @Override
    public List<GamePlayer> findPlayers(Game game) {
        return gamePlayerRepository.findByGameId(game.id().value())
                .map(GamePlayerEntity::toModel)
                .toList();
    }
}
