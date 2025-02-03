package com.julien.plop.game.presenter;

import com.julien.plop.auth.domain.AuthException;
import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.GameGeneratorUseCase;
import com.julien.plop.player.domain.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/games")
public class GameController {

    private final AuthUseCase auth;

    private final GameGeneratorUseCase gameGeneratorUseCase;

    public GameController(AuthUseCase auth, GameGeneratorUseCase gameGeneratorUseCase) {
        this.auth = auth;
        this.gameGeneratorUseCase = gameGeneratorUseCase;
    }

    @PostMapping({"/generate", "/generate/"})
    public GameResponseDTO generate(
            @RequestHeader("Authorization") String rawToken,
            @RequestBody GameRequestDTO request
    ) {
        try {
            Player player = auth.find(rawToken);
            Game game = gameGeneratorUseCase.apply(player, request.code());
            return GameResponseDTO.fromModel(game);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        } catch (GameException e) {
            if (Objects.requireNonNull(e.type()) == GameException.Type.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


}
