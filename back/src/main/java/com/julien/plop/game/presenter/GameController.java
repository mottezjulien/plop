package com.julien.plop.game.presenter;

import com.julien.plop.auth.domain.AuthException;
import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.usecase.GameGeneratorUseCase;
import com.julien.plop.game.domain.usecase.GameMoveUseCase;
import com.julien.plop.player.domain.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/games")
public class GameController {

    private final AuthUseCase auth;

    private final GameMoveUseCase moveUseCase;

    private final GameGeneratorUseCase gameGeneratorUseCase;


    public GameController(AuthUseCase auth, GameMoveUseCase moveUseCase, GameGeneratorUseCase gameGeneratorUseCase) {
        this.auth = auth;
        this.moveUseCase = moveUseCase;
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
            switch (e.type()) {
                case TEMPLATE_NOT_FOUND:
                case GAME_NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
        }
    }

//https://e297-2a01-e0a-db5-bd20-8d7-19a-4d68-b369.ngrok-free.app/games/585d5604-d0be-4bf1-b073-f54507167a07/move

    /*
    0 = {map entry} "Content-Type" -> "application/json; charset=UTF-8"
1 = {map entry} "Accept" -> "application/json"
2 = {map entry} "Authorization" -> "cc6bad61-19aa-4f20-b162-286213057639"
     */
    @PostMapping({"/{gameId}/move", "/{gameId}/move/"}) //TODO name ??
    public GameMoveResponseDTO move(
            @RequestHeader("Authorization") String rawToken,
            @PathVariable("gameId") String gameIdStr,
            @RequestBody GameMoveRequestDTO request
    ) {
        try {
            Player player = auth.find(rawToken);
            moveUseCase.apply(new Game.Id(gameIdStr), player.id(), request.toModel());
            return new GameMoveResponseDTO();
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        } catch (GameException e) {
            switch (e.type()) {
                case GAME_NOT_FOUND:
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The game is not found", e);
                case PLAYER_NOT_IN_GAME:
                    throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The player is not in the game", e);
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
            }
        }
    }

}
