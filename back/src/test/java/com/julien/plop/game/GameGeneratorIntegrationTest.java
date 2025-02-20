package com.julien.plop.game;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.julien.plop.tools.StringTools;
import com.julien.plop.auth.persistence.AuthEntity;
import com.julien.plop.auth.persistence.AuthRepository;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.game.presenter.GameResponseDTO;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.player.persistence.PlayerRepository;
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
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameGeneratorIntegrationTest {

    public static final String TEMPLATE_CODE = "test-template";
    @Value(value = "${local.server.port}")
    private int port;


    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TemplateRepository templateRepository;

    @BeforeEach
    public void init() {
        gameRepository.deleteAll();
    }

    @Test
    public void generateGameWithFirstScenario() {

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(StringTools.generate());
        playerEntity.setName("Julien");
        playerRepository.save(playerEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setId(StringTools.generate());
        authEntity.setToken("testToken");
        authEntity.setDeviceId("anyDevice");
        authEntity.setPlayer(playerEntity);
        authEntity.setDateTime(Instant.now());
        authRepository.save(authEntity);

        String jsonRequest = "{\"code\": \"" + TEMPLATE_CODE + "\"}";

        String gameId;

        try (var client = HttpClient.newBuilder().build()) {
            var request = HttpRequest.newBuilder(URI.create("http://localhost:" + port + "/games/generate"))
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "testToken")
                    .method("POST", HttpRequest.BodyPublishers.ofString(jsonRequest))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assertThat(response.statusCode()).isEqualTo(200);

            GameResponseDTO responseDTO = new ObjectMapper().readValue(response.body(), GameResponseDTO.class);
            assertThat(responseDTO.id()).isNotNull();
            gameId = responseDTO.id();
            assertThat(responseDTO.label()).isEqualTo("Mon premier jeu");


        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        TemplateEntity firstTemplate = templateRepository.findByCode(TEMPLATE_CODE).orElseThrow();

        GameEntity found = gameRepository.findByIdFetchAll(gameId).orElseThrow();
        assertThat(found.getLabel()).isEqualTo("Mon premier jeu");
        assertThat(found.getScenario().getId()).isEqualTo(firstTemplate.getScenario().getId());
        assertThat(found.getBoard().getId()).isEqualTo(firstTemplate.getBoard().getId());

    }

}
