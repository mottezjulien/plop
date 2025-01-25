package com.julien.plop.game.presenter;

import com.julien.plop.auth.Auth;
import com.julien.plop.game.Game;
import com.julien.plop.game.GameException;
import com.julien.plop.game.GameGeneratorUseCase;
import com.julien.plop.player.domain.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/games")
public class GameController {

    private final Auth auth;

    private final GameGeneratorUseCase gameGeneratorUseCase;

    public GameController(Auth auth, GameGeneratorUseCase gameGeneratorUseCase) {
        this.auth = auth;
        this.gameGeneratorUseCase = gameGeneratorUseCase;
    }

    @PostMapping({"/generate", "/generate/"})
    public GameResponseDTO generate(
            @RequestHeader("Authorization") String rawToken,
            @RequestBody GameRequestDTO request
    ) {
        //1) Identify Player -> DONE
        //2) Récupèrer Template -> OK (Board, Scenario, Players ?)
        //3) Créer Game
        //4) Créer Board
        //5) Créer Scenario
        //6) Save Game - InMemory
        //7) Ajout Player (SAVE RELATION ?)

        try {
            //Player player = auth.find(rawToken);
            Player player = new Player(new Player.Id("any"), "Julien");
            Game game = gameGeneratorUseCase.apply(player, request.code());
            return GameResponseDTO.fromModel(game);
        //} catch (Auth.Except e) {
        //    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token", e);
        } catch (GameException e) {
            switch (e.type()) {
                case NOT_FOUND -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
                default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
        }
    }



}
