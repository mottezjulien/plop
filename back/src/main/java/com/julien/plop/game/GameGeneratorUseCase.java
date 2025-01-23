package com.julien.plop.game;

import com.julien.plop.template.Template;
import com.julien.plop.player.domain.model.Player;

import java.util.Optional;

public class GameGeneratorUseCase {

    public interface DataOutput {

        Optional<Template> findByCode(String code);

        void save(Game game);

    }

    private final DataOutput dataOutput;

    public GameGeneratorUseCase(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    public Game apply(Player player, String code) throws GameException {
        Template template = dataOutput.findByCode(code)
                .orElseThrow(() -> new GameException(GameException.Type.NOT_FOUND));
        Game game = template.generate();
        game.addPlayer(player);
        dataOutput.save(game);
        return game;
    }

}
