package com.julien.plop.game;


import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{code}/")
    public GameResponseDTO findByCode(@PathParam("code") String code) {
        return gameRepository.findByCode(code)
                .map(entity -> toResponseDTO(entity))
                .orElseThrow(() -> new HttpNotFoundException());
    }


}
