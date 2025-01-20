package com.julien.plop.game.domain;

import com.julien.plop.game.domain.model.Game;
import com.julien.plop.game.domain.model.GameTemplate;
import com.julien.plop.player.domain.model.Player;

public class GameGeneratorUseCase {


    public Game apply(Player player, GameTemplate template) {
        Game game = template.generate();
        game.addPlayer(player);
        return game;
    }


}
