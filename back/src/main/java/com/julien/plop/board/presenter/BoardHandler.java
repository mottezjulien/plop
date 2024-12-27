package com.julien.plop.board.presenter;


import com.julien.plop.Cache;
import com.julien.plop.board.domain.Board;
import com.julien.plop.board.domain.BoardId;
import com.julien.plop.board.persistence.BoardEntity;
import com.julien.plop.board.persistence.BoardRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class BoardHandler {

    private final BoardRepository repository;

    private final Cache<BoardId, Board> cache = new Cache<>(Duration.ofMinutes(5));

    public BoardHandler(BoardRepository repository) {
        this.repository = repository;
    }

    public Optional<Board> findById(BoardId id) {
        Board board = cache.get(id).orElseGet(() -> refresh(id));
        return Optional.ofNullable(board);
    }

    private Board refresh(BoardId id) {
        Optional<Board> optBoard = repository.findById(id.value()).map(BoardEntity::toModel);
        optBoard.ifPresent(model -> cache.put(id, model));
        return optBoard.orElse(null);
    }

}