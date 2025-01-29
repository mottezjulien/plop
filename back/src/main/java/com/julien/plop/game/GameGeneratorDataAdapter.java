package com.julien.plop.game;

import com.julien.plop.board.persistence.BoardEntity;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.scenario.persistence.ScenarioEntity;
import com.julien.plop.template.Template;
import com.julien.plop.template.persistence.TemplateRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameGeneratorDataAdapter implements GameGeneratorUseCase.DataOutput {

    private final GameRepository repository;
    private final TemplateRepository templateRepository;

    public GameGeneratorDataAdapter(GameRepository repository, TemplateRepository templateRepository) {
        this.repository = repository;
        this.templateRepository = templateRepository;
    }

    @Override
    public Optional<Template> findByCode(String code) {
        return templateRepository.findByCode(code);
    }

    @Override
    public Game createFromTemplate(Template template) {

        GameEntity entity = new GameEntity();
        entity.setTemplateId(template.id().value());
        entity.setTemplateVersion(template.version());
        entity.setLabel(template.label());

        ScenarioEntity scenarioEntity = new ScenarioEntity();
        scenarioEntity.setId(template.scenario().id().value());
        entity.setScenario(scenarioEntity);

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(template.board().id().value());
        entity.setBoard(boardEntity);

        entity = repository.save(entity);

        return new Game(new Game.Id(entity.getId()), template.id(), template.label(), template.version(), template.scenario(), template.board());
    }

    @Override
    public void insert(Game game, Player player) throws GameException {
        GameEntity entity = repository.findByIdFetchAll(game.id().value())
                .orElseThrow(() -> new GameException(GameException.Type.NOT_FOUND));

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(game.id().value());
        entity.getPlayers().add(playerEntity);

        repository.save(entity);
    }

}
