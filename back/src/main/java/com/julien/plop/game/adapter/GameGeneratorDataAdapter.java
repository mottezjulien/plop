package com.julien.plop.game.adapter;

import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.GameGeneratorUseCase;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GamePlayerActionEntity;
import com.julien.plop.game.persistence.GamePlayerActionRepository;
import com.julien.plop.game.persistence.GamePlayerEntity;
import com.julien.plop.game.persistence.GamePlayerRepository;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.scenario.persistence.ScenarioEntity;
import com.julien.plop.template.domain.Template;
import com.julien.plop.template.persistence.TemplateEntity;
import com.julien.plop.template.persistence.TemplateRepository;
import com.julien.plop.tools.StringTools;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class GameGeneratorDataAdapter implements GameGeneratorUseCase.DataOutput {

    private final GameRepository gameRepository;

    private final GamePlayerRepository gamePlayerRepository;
    private final TemplateRepository templateRepository;
    private final GamePlayerActionRepository gamePlayerActionRepository;

    public GameGeneratorDataAdapter(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, TemplateRepository templateRepository, GamePlayerActionRepository gamePlayerActionRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.templateRepository = templateRepository;
        this.gamePlayerActionRepository = gamePlayerActionRepository;
    }

    @Override
    public Optional<Template> findByCode(String code) {
        return templateRepository.findByCode(code).map(TemplateEntity::toModel);
    }

    @Override
    public Game createFromTemplate(Template template) {

        GameEntity entity = new GameEntity();
        entity.setState(Game.State.INIT);
        entity.setId(StringTools.generate());
        entity.setTemplateId(template.id().value());
        entity.setTemplateVersion(template.version());
        entity.setLabel(template.label());

        ScenarioEntity scenarioEntity = new ScenarioEntity();
        scenarioEntity.setId(template.scenario().id().value());
        entity.setScenario(scenarioEntity);

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(template.board().id().value());
        entity.setBoard(boardEntity);

        entity = gameRepository.save(entity);

        return new Game(new Game.Id(entity.getId()), template.id(), template.label(), template.version(), template.scenario(), template.board());
    }

    @Override
    public void insert(Game game, Player player) {
        GamePlayerEntity gamePlayerEntity = new GamePlayerEntity();
        gamePlayerEntity.setId(new GamePlayerEntity.GamePlayerId(game.id().value(), player.id().value()));
        gamePlayerRepository.save(gamePlayerEntity);

        GamePlayerActionEntity gamePlayerActionEntity = new GamePlayerActionEntity();
        gamePlayerActionEntity.setId(StringTools.generate());
        gamePlayerActionEntity.setType(GamePlayerActionEntity.Type.INIT);
        gamePlayerActionEntity.setDate(Instant.now());
        gamePlayerActionRepository.save(gamePlayerActionEntity);

    }

}
