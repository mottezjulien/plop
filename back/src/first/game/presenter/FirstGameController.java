package com.julien.plop.first.game.presenter;


import com.julien.plop.first.game.FirstGameState;
import com.julien.plop.first.game.persistence.FirstGameEntity;
import com.julien.plop.first.game.persistence.FirstGameRepository;
import com.julien.plop.generic.presenter.exception.BadRequestHttpException;
import com.julien.plop.generic.presenter.exception.HttpException;
import com.julien.plop.generic.presenter.exception.NotFoundHttpException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/first/games")
public class FirstGameController {

    private final FirstGameRepository repository;

    public FirstGameController(FirstGameRepository repository) {
        this.repository = repository;
    }

    @PostMapping({"/", ""})
    public GameResponseDTO create(@RequestBody GameRequestDTO request) {
        FirstGameEntity entity = new FirstGameEntity();
        entity.setLabel(request.label());
        entity.setState(FirstGameState.INIT);
        return GameResponseDTO.fromEntity(repository.save(entity));
    }

    @GetMapping({"/{gameId}/", "/{gameId}/"})
    public GameResponseDTO findOne(@PathVariable("gameId") String gameId) throws NotFoundHttpException {
        return GameResponseDTO.fromEntity(repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new));
    }

    @DeleteMapping({"/{gameId}/", "/{gameId}/"})
    public void delete(@PathVariable("gameId") String gameId) throws NotFoundHttpException {
        FirstGameEntity entity = repository.findById(gameId).orElseThrow(NotFoundHttpException::new);
        repository.delete(entity);
    }

    @GetMapping({"/{gameId}/start", "/{gameId}/start/"})
    public GameResponseDTO start(@PathVariable("gameId") String gameId) throws HttpException {
        FirstGameEntity entity = repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new);
        if(entity.getState() == FirstGameState.INIT) {
            entity.setState(FirstGameState.PLAYING);
            repository.save(entity);
            return GameResponseDTO.fromEntity(entity);
        }
        throw new BadRequestHttpException("Game state invalid");
    }

    @GetMapping({"/{gameId}/stop", "/{gameId}/stop/"})
    public GameResponseDTO stop(@PathVariable("gameId") String gameId) throws HttpException {
        FirstGameEntity entity = repository.findById(gameId)
                .orElseThrow(NotFoundHttpException::new);
        if(entity.getState() == FirstGameState.PLAYING) {
            entity.setState(FirstGameState.ENDED);
            repository.save(entity);
            return GameResponseDTO.fromEntity(entity);
        }
        throw new BadRequestHttpException("Game state invalid");
    }

}
