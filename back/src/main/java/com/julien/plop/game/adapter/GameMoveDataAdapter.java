package com.julien.plop.game.adapter;


import com.julien.plop.board.model.Board;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.board.persistence.repository.BoardRepository;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.usecase.GameMoveUseCase;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.game.persistence.GamePlayerActionEntity;
import com.julien.plop.game.persistence.GamePlayerActionRepository;
import com.julien.plop.game.persistence.GamePlayerEntity;
import com.julien.plop.tools.StringTools;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class GameMoveDataAdapter implements GameMoveUseCase.DataOutput {

    private final BoardRepository boardRepository;
    private final GamePlayerActionRepository gamePlayerActionRepository;

    public GameMoveDataAdapter(BoardRepository boardRepository, GamePlayerActionRepository gamePlayerActionRepository) {
        this.boardRepository = boardRepository;
        this.gamePlayerActionRepository = gamePlayerActionRepository;
    }


    @Override
    public Optional<Board> findBoard(Game.Id gameId) {
        return boardRepository.findByGameIdFetchSpacesAndRects(gameId.value())
                .map(BoardEntity::toModel);
    }

    @Override
    public void savePosition(GamePlayer gamePlayer) throws GameException {

        GamePlayerEntity.GamePlayerId id = new GamePlayerEntity.GamePlayerId(
                gamePlayer.gameId().value(), gamePlayer.playerId().value());
        GamePlayerEntity entity = new GamePlayerEntity();
        entity.setId(id);

        BoardSpace.Point position = gamePlayer.position()
                .orElseThrow(() -> new GameException(GameException.Type.PLAYER_NOT_SET));

        GamePlayerActionEntity action = new GamePlayerActionEntity();
        action.setId(StringTools.generate());
        action.setType(GamePlayerActionEntity.Type.MOVE);
        action.setDate(Instant.now());
        action.setGamePlayer(entity);
        action.setJsonValues(StringTools.toJson(position));
        gamePlayerActionRepository.save(action);

    }

}
