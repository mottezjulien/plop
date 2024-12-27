package com.julien.plop.board.persistence;

import com.julien.plop.board.domain.Board;
import com.julien.plop.board.domain.BoardId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_BOARD")
public class BoardEntity {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "game_id")
    private String gameId;

    @OneToMany(mappedBy = "board")
    private Set<BoardSpaceEntity> spaces = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Set<BoardSpaceEntity> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<BoardSpaceEntity> spaces) {
        this.spaces = spaces;
    }

    public Board toModel() {
        return new Board(
                new BoardId(id),
                spaces.stream()
                .map(BoardSpaceEntity::toModel)
                .toList());
    }
}
