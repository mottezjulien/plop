package com.julien.plop.game.domain.usecase;

import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.template.domain.Template;

import java.util.Optional;

public class GameGeneratorUseCase {

    public interface DataOutput {

        Optional<Template> findById(Template.Id templateId);

        Game createFromTemplate(Template template);

        void insert(Game game, Player player) throws GameException;
    }

    private final DataOutput dataOutput;

    public GameGeneratorUseCase(DataOutput dataOutput) {
        this.dataOutput = dataOutput;
    }

    public Game apply(Player player, Template.Id templateId) throws GameException {
        Template template = dataOutput.findById(templateId)
                .orElseThrow(() -> new GameException(GameException.Type.TEMPLATE_NOT_FOUND));
        Game game = dataOutput.createFromTemplate(template);
        dataOutput.insert(game, player);
        return game;
    }

}
