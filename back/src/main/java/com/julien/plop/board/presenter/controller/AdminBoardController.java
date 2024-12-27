package com.julien.plop.board.presenter.controller;

import com.julien.plop.board.persistence.BoardEntity;
import com.julien.plop.board.persistence.BoardRepository;
import com.julien.plop.board.presenter.dto.BoardRequestDTO;
import com.julien.plop.board.presenter.dto.BoardResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/boards")
public class AdminBoardController {

    private final BoardRepository repository;

    public AdminBoardController(BoardRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public BoardResponseDTO create(@RequestBody BoardRequestDTO request) {
        BoardEntity entity = new BoardEntity();
        entity.setGameId(request.gameId());
        return BoardResponseDTO.fromEntity(repository.save(entity));
    }

}
