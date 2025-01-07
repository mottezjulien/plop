package com.julien.plop.board.presenter.controller;

import com.julien.plop.board.persistence.entity.BoardEntity;
import com.julien.plop.board.persistence.entity.BoardPointEntity;
import com.julien.plop.board.persistence.entity.BoardRectEntity;
import com.julien.plop.board.persistence.repository.BoardPointRepository;
import com.julien.plop.board.persistence.repository.BoardRectRepository;
import com.julien.plop.board.persistence.repository.BoardRepository;
import com.julien.plop.board.persistence.entity.BoardSpaceEntity;
import com.julien.plop.board.persistence.repository.BoardSpaceRepository;
import com.julien.plop.board.presenter.dto.BoardRectRequestDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceRequestDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceResponseDTO;
import com.julien.plop.generic.presenter.exception.HttpException;
import com.julien.plop.generic.presenter.exception.NotFoundHttpException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/spaces")
public class BoardSpacesController {

    private final BoardRepository boardRepository;
    private final BoardSpaceRepository spaceRepository;
    private final BoardRectRepository rectRepository;
    private final BoardPointRepository pointRepository;

    public BoardSpacesController(BoardRepository boardRepository, BoardSpaceRepository spaceRepository, BoardRectRepository rectRepository, BoardPointRepository pointRepository) {
        this.boardRepository = boardRepository;
        this.spaceRepository = spaceRepository;
        this.rectRepository = rectRepository;
        this.pointRepository = pointRepository;
    }

    @GetMapping({"/", "",})
    public BoardSpaceResponseDTO create(
            @PathVariable("boardId") String boardId,
            @RequestBody BoardSpaceRequestDTO request) throws HttpException {
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(NotFoundHttpException::new);
        BoardSpaceEntity entity = new BoardSpaceEntity();
        entity.setBoard(boardEntity);
        entity.setLabel(request.label());
        return BoardSpaceResponseDTO.fromEntity(spaceRepository.save(entity));
    }



    @PostMapping({"/{spaceId}", "/{spaceId}/"})
    public BoardSpaceResponseDTO addRect(
            @PathVariable("boardId") String boardId,
            @PathVariable("spaceId") String spaceId,
            @RequestBody BoardRectRequestDTO request) throws HttpException {

        BoardEntity boardEntity = boardRepository.findByIdFetchSpaces(boardId)
                .orElseThrow(NotFoundHttpException::new);

        BoardSpaceEntity spaceEntity = boardEntity.getSpaces().stream()
                .filter(space -> space.getId().equals(spaceId))
                .findFirst()
                .orElseThrow(NotFoundHttpException::new);

        BoardPointEntity bottomLeftEntity = new BoardPointEntity();
        bottomLeftEntity.setLat(request.bottomLeft().lat());
        bottomLeftEntity.setLng(request.bottomLeft().lng());
        pointRepository.save(bottomLeftEntity);

        BoardPointEntity topRightEntity = new BoardPointEntity();
        topRightEntity.setLat(request.topRight().lat());
        topRightEntity.setLng(request.topRight().lng());
        pointRepository.save(topRightEntity);

        BoardRectEntity rectEntity = new BoardRectEntity();
        rectEntity.setSpace(spaceEntity);
        rectEntity.setBottomLeft(bottomLeftEntity);
        rectEntity.setTopRight(topRightEntity);
        spaceEntity.getRects().add(rectRepository.save(rectEntity));

        return BoardSpaceResponseDTO.fromEntity(spaceEntity);
    }


}
