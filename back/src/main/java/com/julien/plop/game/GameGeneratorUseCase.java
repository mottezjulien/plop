package com.julien.plop.game;

import com.julien.plop.player.domain.model.Player;
import com.julien.plop.template.Template;

import java.util.Optional;

public class GameGeneratorUseCase {

    public interface DataOutput {

        Optional<Template> findByCode(String code);

        Game createFromTemplate(Template template);

        void insert(Game game, Player player) throws GameException;
    }

    private final DataOutput dataOutput;

    public GameGeneratorUseCase(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    public Game apply(Player player, String code) throws GameException {
        Template template = dataOutput.findByCode(code)
                .orElseThrow(() -> new GameException(GameException.Type.NOT_FOUND));
        Game game = dataOutput.createFromTemplate(template);
        dataOutput.insert(game, player);
        game.addPlayer(player);
        return game;
    }

}
