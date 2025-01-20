package com.julien.plop.presenter;

import com.julien.plop.auth.AuthException;
import com.julien.plop.auth.AuthUseCase;
import com.julien.plop.game.domain.GameGeneratorUseCase;
import com.julien.plop.game.domain.model.Game;
import com.julien.plop.game.domain.model.GameTemplate;
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

    private final AuthUseCase authUseCase;

    private final GameGeneratorUseCase gameGeneratorUseCase;

    public GameController(AuthUseCase authUseCase, GameGeneratorUseCase gameGeneratorUseCase) {
        this.authUseCase = authUseCase;
        this.gameGeneratorUseCase = gameGeneratorUseCase;
    }

    @PostMapping("/generate")
    public GameResponseDTO generate(
            @RequestHeader("Authorization") String rawToken,
            @RequestBody GameRequestDTO request
    ) {
        try {
            Player player = authUseCase.get(rawToken);

            GameTemplate template = tamplateRepositoy.findByCode(request.code())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Template not found"));

            Game game = gameGeneratorUseCase.apply(player, template);

            return GameResponseDTO.fromModel(game);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token", e);
        }
    }



}
