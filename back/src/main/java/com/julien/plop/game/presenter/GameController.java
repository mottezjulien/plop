package com.julien.plop.game.presenter;


import com.julien.plop.game.GameState;
import com.julien.plop.game.persistence.GameEntity;
import com.julien.plop.game.persistence.GameRepository;
import com.julien.plop.generic.presenter.exception.BadRequestHttpException;
import com.julien.plop.generic.presenter.exception.HttpException;
import com.julien.plop.generic.presenter.exception.NotFoundHttpException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @PostMapping({"/", ""})
    public GameResponseDTO create(@RequestBody GameRequestDTO request) {
        GameEntity entity = new GameEntity();
        entity.setLabel(request.label());
        entity.setState(GameState.INIT);
        return GameResponseDTO.fromEntity(repository.save(entity));
    }

    @GetMapping({"/{gameId}/", "/{gameId}/"})
    public GameResponseDTO findOne(@PathVariable("gameId") String gameId) throws NotFoundHttpException {
        return GameResponseDTO.fromEntity(repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new));
    }

    @DeleteMapping({"/{gameId}/", "/{gameId}/"})
    public void delete(@PathVariable("gameId") String gameId) throws NotFoundHttpException {
        GameEntity entity = repository.findById(gameId).orElseThrow(NotFoundHttpException::new);
        repository.delete(entity);
    }

    @GetMapping({"/{gameId}/start", "/{gameId}/start/"})
    public GameResponseDTO start(@PathVariable("gameId") String gameId) throws HttpException {
        GameEntity entity = repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new);
        if(entity.getState() == GameState.INIT) {
            entity.setState(GameState.PLAYING);
            repository.save(entity);
            return GameResponseDTO.fromEntity(entity);
        }
        throw new BadRequestHttpException("Game state invalid");
    }

    @GetMapping({"/{gameId}/stop", "/{gameId}/stop/"})
    public GameResponseDTO stop(@PathVariable("gameId") String gameId) throws HttpException {
        GameEntity entity = repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new);
        if(entity.getState() == GameState.PLAYING) {
            entity.setState(GameState.ENDED);
            repository.save(entity);
            return GameResponseDTO.fromEntity(entity);
        }
        throw new BadRequestHttpException("Game state invalid");
    }

}
