package com.julien.plop.board.presenter.controller;

import com.julien.plop.board.persistence.BoardEntity;
import com.julien.plop.board.persistence.BoardSpaceEntity;
import com.julien.plop.board.persistence.BoardSpaceRepository;
import com.julien.plop.board.presenter.dto.BoardRectDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceDetailsResponseDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceRequestDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/boards/{boardId}/spaces")
public class AdminBoardSpaceController {

    private final BoardSpaceRepository spaceRepository;

    public AdminBoardSpaceController(BoardSpaceRepository repository) {
        this.spaceRepository = repository;
    }

    @PostMapping("/")
    public BoardSpaceResponseDTO create(@PathVariable("boardId") String boardId, @RequestBody BoardSpaceRequestDTO request) {
        BoardSpaceEntity entity = new BoardSpaceEntity();
        entity.setBoard(boardEntity(boardId));
        entity.setLabel(request.label());
        entity.setPriority(request.priority());
        return BoardSpaceResponseDTO.fromEntity(spaceRepository.save(entity));
    }

    @PostMapping("/{spaceId}/")
    public BoardSpaceResponseDTO update(@PathVariable("boardId") String boardId,
                                        @PathVariable("spaceId") String spaceId, 
                                        @RequestBody BoardSpaceRequestDTO request) {
        BoardSpaceEntity entity = spaceRepository.findById(spaceId).orElseThrow();
        entity.setLabel(request.label());
        entity.setPriority(request.priority());
        return BoardSpaceResponseDTO.fromEntity(spaceRepository.save(entity));
    }

    @PutMapping("/{spaceId}/rect/")
    public BoardSpaceDetailsResponseDTO addRect(@PathVariable("boardId") String boardId,
                                                @PathVariable("spaceId") String spaceId,
                                                @RequestBody BoardRectDTO request) {
        BoardSpaceEntity entity = spaceRepository.findByIdFetchs(spaceId).orElseThrow();
        entity.getRects().add(request.toEntity());
        spaceRepository.save(entity);
        return BoardSpaceDetailsResponseDTO.fromEntity(entity);
    }

    @GetMapping("/")
    public List<BoardSpaceResponseDTO> findByBoardId(@PathVariable("boardId") String boardId) {
        List<BoardSpaceEntity> spaces = spaceRepository.findByBoardId(boardId);
        return spaces.stream()
                .map(BoardSpaceResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{spaceId}/")
    public BoardSpaceDetailsResponseDTO findOne(@PathVariable("boardId") String boardId, @PathVariable("spaceId") String spaceId) {
        BoardSpaceEntity entity = spaceRepository.findByIdFetchs(spaceId).orElseThrow();
        return BoardSpaceDetailsResponseDTO.fromEntity(entity);
    }


    private BoardEntity boardEntity(String boardId) {
        BoardEntity board = new BoardEntity();
        board.setId(boardId);
        return board;
    }


}
