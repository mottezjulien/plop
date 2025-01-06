package com.julien.plop.board.presenter.controller;

import com.julien.plop.board.persistence.BoardEntity;
import com.julien.plop.board.persistence.BoardRepository;
import com.julien.plop.board.persistence.BoardSpaceEntity;
import com.julien.plop.board.persistence.BoardSpaceRepository;
import com.julien.plop.board.presenter.dto.BoardSpaceRequestDTO;
import com.julien.plop.board.presenter.dto.BoardResponseDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceResponseDTO;
import com.julien.plop.generic.presenter.exception.HttpException;
import com.julien.plop.generic.presenter.exception.NotFoundHttpException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/spaces")
public class BoardSpacesController {

    private BoardRepository boardRepository;
    private BoardSpaceRepository spaceRepository;

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
        BoardEntity boardEntity = boardRepository.findById(boardId)
                .orElseThrow(NotFoundHttpException::new);
        BoardSpaceEntity entity = new BoardSpaceEntity();
        entity.setBoard(boardEntity);
        entity.setLabel(request.label());
        entity.setPriority(0);
        return BoardSpaceResponseDTO.fromEntity(spaceRepository.save(entity));
    }

    /*
    private final BoardHandler boardHandler;

    public BoardSpacesController(BoardHandler boardHandler) {
        this.boardHandler = boardHandler;
    }


    @GetMapping("/")
    public List<BoardSpaceResponseDTO> findByPoint(@PathVariable("boardId") String boardId,
                                                   @RequestBody BoardPointDTO point) {
        Board board = boardHandler.findById(new BoardId(boardId)).orElseThrow();
        return board.findSpaces(point.toModel()).map(BoardSpaceResponseDTO::fromModel).toList();
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }


    public class Greeting {

        private String content;

        public Greeting() {
        }

        public Greeting(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

    }


    public class HelloMessage {

        private String name;

        public HelloMessage() {
        }

        public HelloMessage(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }*/

}
