package com.julien.plop.game.adapter;

import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameVerifyUseCase;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.player.domain.model.Player;
import org.springframework.stereotype.Component;

@Component
public class GameVerifyDataAdapter implements GameVerifyUseCase.DataOutput {

    private final GameRepository repository;

    public GameVerifyDataAdapter(GameRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isExist(Game.Id gameId) {
        return repository.existsById(gameId.value());
    }

    @Override
    public boolean isStarted(Game.Id gameId) {
        return repository.isStarted(gameId.value());
    }

    @Override
    public boolean isIn(Game.Id gameId, Player.Id playerId) {
        return repository.isIn(gameId.value(), playerId.value());
    }

}
