package com.julien.plop.game;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.julien.plop.auth.Auth;
import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.board.persistence.entity.BoardRectEntity;
import com.julien.plop.board.persistence.entity.BoardSpaceEntity;
import com.julien.plop.board.persistence.repository.BoardRectRepository;
import com.julien.plop.board.persistence.repository.BoardRepository;
import com.julien.plop.board.persistence.repository.BoardSpaceRepository;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.game.presenter.GameResponseDTO;
import com.julien.plop.generic.AndOrOr;
import com.julien.plop.i18n.persistence.I18nEntity;
import com.julien.plop.i18n.persistence.I18nRepository;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.player.persistence.PlayerRepository;
import com.julien.plop.scenario.persistence.ScenarioEntity;
import com.julien.plop.scenario.persistence.ScenarioRepository;
import com.julien.plop.scenario.persistence.ScenarioStepEntity;
import com.julien.plop.scenario.persistence.ScenarioStepRepository;
import com.julien.plop.scenario.persistence.ScenarioTargetEntity;
import com.julien.plop.scenario.persistence.ScenarioTargetRepository;
import com.julien.plop.scenario.persistence.possibility.ScenarioPossibilityEntity;
import com.julien.plop.scenario.persistence.possibility.ScenarioPossibilityRepository;
import com.julien.plop.scenario.persistence.possibility.condition.ScenarioPossibilityConditionRepository;
import com.julien.plop.scenario.persistence.possibility.condition.entity.ScenarioPossibilityConditionInsideSpaceEntity;
import com.julien.plop.scenario.persistence.possibility.consequence.ScenarioPossibilityConsequenceRepository;
import com.julien.plop.scenario.persistence.possibility.consequence.entity.ScenarioPossibilityConsequenceEndedStepEntity;
import com.julien.plop.scenario.persistence.possibility.trigger.ScenarioPossibilityTriggerRepository;
import com.julien.plop.scenario.persistence.possibility.trigger.entity.ScenarioPossibilityTriggerGoOutSpaceEntity;
import com.julien.plop.template.persistence.TemplateEntity;
import com.julien.plop.template.persistence.TemplateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameGeneratorIntegrationTest {

    refacto test

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private Auth auth;
    @Autowired
    private I18nRepository i18nRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardSpaceRepository boardSpaceRepository;
    @Autowired
    private BoardRectRepository boardRectRepository;

    @Autowired
    private ScenarioRepository scenarioRepository;
    @Autowired
    private ScenarioStepRepository scenarioStepRepository;
    @Autowired
    private ScenarioTargetRepository scenarioTargetRepository;
    @Autowired
    private ScenarioPossibilityRepository possibilityRepository;
    @Autowired
    private ScenarioPossibilityTriggerRepository triggerRepository;
    @Autowired
    private ScenarioPossibilityConditionRepository conditionRepository;
    @Autowired
    private ScenarioPossibilityConsequenceRepository consequenceRepository;

    @BeforeEach
    public void init() {
        i18nRepository.deleteAll();

        scenarioTargetRepository.deleteAll();
        scenarioStepRepository.deleteAll();
        scenarioRepository.deleteAll();

        boardRepository.deleteAll();

        templateRepository.deleteAll();
        gameRepository.deleteAll();
    }

    @Test
    public void plop() {

        BoardEntity boardEntity = new BoardEntity();
        boardRepository.save(boardEntity);

        BoardSpaceEntity spaceEntity = new BoardSpaceEntity();
        spaceEntity.setBoard(boardEntity);
        spaceEntity.setLabel("La gare");
        boardSpaceRepository.save(spaceEntity);

        BoardRectEntity rectEntity = new BoardRectEntity();
        rectEntity.setSpace(spaceEntity);
        rectEntity.setTopRightLatitude(1.0f);
        rectEntity.setTopRightLongitude(6.098760f);
        rectEntity.setBottomLeftLatitude(5.098765f);
        rectEntity.setBottomLeftLongitude(60.980f);
        boardRectRepository.save(rectEntity);

        ScenarioEntity scenarioEntity = new ScenarioEntity();
        scenarioEntity.setLabel("Mon nouveau scénario");
        scenarioRepository.save(scenarioEntity);

        ScenarioStepEntity stepEntity = new ScenarioStepEntity();
        stepEntity.setLabel("Chapitre 1");
        stepEntity.setScenario(scenarioEntity);
        scenarioStepRepository.save(stepEntity);

        I18nEntity target1Label = new I18nEntity();
        target1Label.setDescription("RDV à la gare");
        target1Label.setJsonValues("{\"fr\":\"RDV à la gare\"}");
        i18nRepository.save(target1Label);

        ScenarioTargetEntity target1Entity = new ScenarioTargetEntity();
        target1Entity.setLabel(target1Label);
        target1Entity.setStep(stepEntity);
        scenarioTargetRepository.save(target1Entity);

        I18nEntity target2Label = new I18nEntity();
        target2Label.setDescription("Aller à la piscine");
        target2Label.setJsonValues("{\"fr\":\"Aller à la piscine\"}");
        i18nRepository.save(target2Label);

        ScenarioTargetEntity scenarioTarget2Entity = new ScenarioTargetEntity();
        scenarioTarget2Entity.setLabel(target2Label);
        scenarioTarget2Entity.setStep(stepEntity);
        scenarioTargetRepository.save(scenarioTarget2Entity);

        ScenarioPossibilityTriggerGoOutSpaceEntity triggerEntity = new ScenarioPossibilityTriggerGoOutSpaceEntity();
        triggerEntity.setSpaceId(spaceEntity.getId());
        triggerRepository.save(triggerEntity);

        ScenarioPossibilityConditionInsideSpaceEntity conditionEntity = new ScenarioPossibilityConditionInsideSpaceEntity();
        conditionEntity.setSpaceId(spaceEntity.getId());
        conditionRepository.save(conditionEntity);

        ScenarioPossibilityConsequenceEndedStepEntity consequenceEntity = new ScenarioPossibilityConsequenceEndedStepEntity();
        consequenceEntity.setStepId(stepEntity.getId());
        consequenceRepository.save(consequenceEntity);

        ScenarioPossibilityEntity possibilityEntity = new ScenarioPossibilityEntity();
        possibilityEntity.setTrigger(triggerEntity);
        possibilityEntity.setConditionType(AndOrOr.AND);
        possibilityEntity.setStep(stepEntity);
        possibilityEntity.getConditions().add(conditionEntity);
        possibilityEntity.getConsequences().add(consequenceEntity);
        possibilityRepository.save(possibilityEntity);

        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setCode("my-code");
        templateEntity.setLabel("Mon nouveau jeu");
        templateEntity.setVersion("1.0.1");
        templateEntity.setScenario(scenarioEntity);
        templateEntity.setBoard(boardEntity);

        templateRepository.save(templateEntity);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName("Julien");
        playerRepository.save(playerEntity);

        Player player = new Player(new Player.Id(playerEntity.getId()), playerEntity.getName());
        String token = auth.generate(player);


        String bobyRequest = """
                {
                    "code": "my-code"
                }
                """;

        String gameId;

        try (var client = HttpClient.newBuilder().build()) {
            var request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/games/generate"))
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", token)
                    .method("POST", HttpRequest.BodyPublishers.ofString(bobyRequest))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertThat(response.statusCode()).isEqualTo(200);

            GameResponseDTO responseDTO = new ObjectMapper().readValue(response.body(), GameResponseDTO.class);
            assertThat(responseDTO.id()).isNotNull();
            gameId = responseDTO.id();
            assertThat(responseDTO.label()).isEqualTo("Mon nouveau jeu");


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        GameEntity found = gameRepository.findByIdFetchAll(gameId).orElseThrow();
        assertThat(found.getLabel()).isEqualTo("Mon nouveau jeu");
        assertThat(found.getScenario().getId()).isEqualTo(scenarioEntity.getId());
        assertThat(found.getBoard().getId()).isEqualTo(boardEntity.getId());

    }

}
