package com.julien.plop.board.presenter.controller;

import com.julien.plop.board.domain.Board;
import com.julien.plop.board.domain.BoardId;
import com.julien.plop.board.presenter.BoardHandler;
import com.julien.plop.board.presenter.dto.BoardPointDTO;
import com.julien.plop.board.presenter.dto.BoardSpaceResponseDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/spaces")
public class BoardSpacesController {

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
    }

}
