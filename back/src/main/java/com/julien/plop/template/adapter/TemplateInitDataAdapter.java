package com.julien.plop.template.adapter;

import com.julien.plop.I18n;
import com.julien.plop.StringTools;
import com.julien.plop.board.model.Board;
import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.board.persistence.entity.BoardRectEntity;
import com.julien.plop.board.persistence.entity.BoardSpaceEntity;
import com.julien.plop.board.persistence.repository.BoardRectRepository;
import com.julien.plop.board.persistence.repository.BoardRepository;
import com.julien.plop.board.persistence.repository.BoardSpaceRepository;
import com.julien.plop.i18n.persistence.I18nEntity;
import com.julien.plop.i18n.persistence.I18nRepository;
import com.julien.plop.scenario.Possibility;
import com.julien.plop.scenario.Scenario;
import com.julien.plop.scenario.persistence.*;
import com.julien.plop.scenario.persistence.possibility.ScenarioPossibilityEntity;
import com.julien.plop.scenario.persistence.possibility.ScenarioPossibilityRepository;
import com.julien.plop.scenario.persistence.possibility.condition.ScenarioPossibilityConditionRepository;
import com.julien.plop.scenario.persistence.possibility.condition.entity.ScenarioPossibilityConditionAbstractEntity;
import com.julien.plop.scenario.persistence.possibility.consequence.ScenarioPossibilityConsequenceRepository;
import com.julien.plop.scenario.persistence.possibility.consequence.entity.ScenarioPossibilityConsequenceAbstractEntity;
import com.julien.plop.scenario.persistence.possibility.trigger.ScenarioPossibilityTriggerRepository;
import com.julien.plop.scenario.persistence.possibility.trigger.entity.ScenarioPossibilityTriggerAbstractEntity;
import com.julien.plop.template.domain.Template;
import com.julien.plop.template.domain.TemplateInitUseCase;
import com.julien.plop.template.persistence.TemplateEntity;
import com.julien.plop.template.persistence.TemplateRepository;
import org.springframework.stereotype.Component;

@Component
public class TemplateInitDataAdapter implements TemplateInitUseCase.OutPort {

    private final I18nRepository i18nRepository;

    private final TemplateRepository templateRepository;

    private final BoardRepository boardRepository;
    private final BoardSpaceRepository boardSpaceRepository;
    private final BoardRectRepository boardRectRepository;

    private final ScenarioRepository scenarioRepository;
    private final ScenarioStepRepository scenarioStepRepository;
    private final ScenarioTargetRepository scenarioTargetRepository;
    private final ScenarioPossibilityRepository possibilityRepository;
    private final ScenarioPossibilityTriggerRepository triggerRepository;
    private final ScenarioPossibilityConditionRepository conditionRepository;
    private final ScenarioPossibilityConsequenceRepository consequenceRepository;

    public TemplateInitDataAdapter(I18nRepository i18nRepository, TemplateRepository templateRepository, BoardRepository boardRepository, BoardSpaceRepository boardSpaceRepository, BoardRectRepository boardRectRepository, ScenarioRepository scenarioRepository, ScenarioStepRepository scenarioStepRepository, ScenarioTargetRepository scenarioTargetRepository, ScenarioPossibilityRepository possibilityRepository, ScenarioPossibilityTriggerRepository triggerRepository, ScenarioPossibilityConditionRepository conditionRepository, ScenarioPossibilityConsequenceRepository consequenceRepository) {
        this.i18nRepository = i18nRepository;
        this.templateRepository = templateRepository;
        this.boardRepository = boardRepository;
        this.boardSpaceRepository = boardSpaceRepository;
        this.boardRectRepository = boardRectRepository;
        this.scenarioRepository = scenarioRepository;
        this.scenarioStepRepository = scenarioStepRepository;
        this.scenarioTargetRepository = scenarioTargetRepository;
        this.possibilityRepository = possibilityRepository;
        this.triggerRepository = triggerRepository;
        this.conditionRepository = conditionRepository;
        this.consequenceRepository = consequenceRepository;
    }


    @Override
    public boolean isEmpty() {
        return templateRepository.count() == 0;
    }

    @Override
    public void createAll(Template template) {
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setId(template.id().value());
        templateEntity.setCode(template.code());
        templateEntity.setLabel(template.label());
        templateEntity.setVersion(template.version());
        templateEntity.setBoard(createBoard(template.board()));
        templateEntity.setScenario(createScenario(template.scenario()));
        templateRepository.save(templateEntity);
    }


    private BoardEntity createBoard(Board board) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(board.id().value());
        boardRepository.save(boardEntity);
        board.spaces().forEach(space -> {
            BoardSpaceEntity spaceEntity = new BoardSpaceEntity();
            spaceEntity.setId(space.id().value());
            spaceEntity.setBoard(boardEntity);
            spaceEntity.setLabel(space.label());
            spaceEntity.setPriority(space.priority());
            boardSpaceRepository.save(spaceEntity);
            space.rects().forEach(rect -> {
                BoardRectEntity rectEntity = new BoardRectEntity();
                rectEntity.setId(StringTools.generate());
                rectEntity.setSpace(spaceEntity);
                rectEntity.setTopRightLatitude(rect.topRight().lat());
                rectEntity.setTopRightLongitude(rect.topRight().lng());
                rectEntity.setBottomLeftLatitude(rect.bottomLeft().lat());
                rectEntity.setBottomLeftLongitude(rect.bottomLeft().lng());
                boardRectRepository.save(rectEntity);
            });
        });
        return boardEntity;
    }


    private ScenarioEntity createScenario(Scenario scenario) {
        ScenarioEntity scenarioEntity = new ScenarioEntity();
        scenarioEntity.setId(scenario.id().value());
        scenarioEntity.setLabel(scenario.label());
        scenarioRepository.save(scenarioEntity);

        scenario.steps().forEach(step -> {
            ScenarioStepEntity stepEntity = new ScenarioStepEntity();
            stepEntity.setId(step.id().value());
            step.label().ifPresent(label -> stepEntity.setLabel(createI18n(label)));
            stepEntity.setScenario(scenarioEntity);
            scenarioStepRepository.save(stepEntity);
            step.targets().forEach(target -> createTarget(target, stepEntity));
            step.possibilities().forEach(possibility -> createPossibility(possibility, stepEntity));
        });
        return scenarioEntity;
    }

    private void createTarget(Scenario.Target target, ScenarioStepEntity stepEntity) {
        ScenarioTargetEntity targetEntity = new ScenarioTargetEntity();
        targetEntity.setId(StringTools.generate());
        target.label().ifPresent(label ->
                targetEntity.setLabel(createI18n(label)));
        target.desc().ifPresent(desc ->
                targetEntity.setDescription(createI18n(desc)));
        targetEntity.setStep(stepEntity);
        targetEntity.setOptional(target.optional());
        scenarioTargetRepository.save(targetEntity);
    }

    private void createPossibility(Possibility possibility, ScenarioStepEntity stepEntity) {

        ScenarioPossibilityEntity possibilityEntity = new ScenarioPossibilityEntity();
        possibilityEntity.setId(possibility.id().value());
        possibilityEntity.setConditionType(possibility.conditionType());
        possibilityEntity.setStep(stepEntity);

        ScenarioPossibilityTriggerAbstractEntity triggerEntity = ScenarioPossibilityTriggerAbstractEntity.fromModel(possibility.trigger());
        possibilityEntity.setTrigger(triggerRepository.save(triggerEntity));

        possibility.conditions().forEach(condition -> {
            ScenarioPossibilityConditionAbstractEntity conditionEntity = ScenarioPossibilityConditionAbstractEntity
                    .fromModel(condition);
            possibilityEntity.getConditions().add(conditionRepository.save(conditionEntity));
        });

        possibility.consequences().forEach(consequence -> {
            ScenarioPossibilityConsequenceAbstractEntity consequenceEntity = ScenarioPossibilityConsequenceAbstractEntity
                    .fromModel(consequence);
            possibilityEntity.getConsequences().add(consequenceRepository.save(consequenceEntity));
        });

        possibilityRepository.save(possibilityEntity);
    }

    private I18nEntity createI18n(I18n i18n) {
        I18nEntity entity = new I18nEntity();
        entity.setId(StringTools.generate());
        entity.setDescription(i18n.description());
        entity.setJsonValues(i18n.json());
        return i18nRepository.save(entity);
    }

}
