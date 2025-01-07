package com.julien.plop.board.presenter.controller;


import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.board.persistence.repository.BoardRepository;
import com.julien.plop.board.presenter.dto.BoardRequestDTO;
import com.julien.plop.board.presenter.dto.BoardResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository repository;

    public BoardController(BoardRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public BoardResponseDTO create(@RequestBody BoardRequestDTO request) {
        return BoardResponseDTO.fromEntity(repository.findByGameId(request.gameId())
                .orElseGet(() -> {
                    BoardEntity entity = new BoardEntity();
                    entity.setGameId(request.gameId());
                    return repository.save(entity);
                }));
    }
}
