package com.julien.plop.board.presenter;

import org.springframework.stereotype.Component;
/*
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

}*/
