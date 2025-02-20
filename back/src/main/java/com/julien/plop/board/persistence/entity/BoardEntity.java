package com.julien.plop.board.persistence.entity;

import com.julien.plop.board.model.Board;
import com.julien.plop.game.persistence.GameEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_BOARD")
public class BoardEntity {

    @Id
    private String id;

    @OneToMany(mappedBy = "board")
    private Set<BoardSpaceEntity> spaces = new HashSet<>();

    @OneToMany(mappedBy = "board")
    private Set<GameEntity> games = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<BoardSpaceEntity> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<BoardSpaceEntity> spaces) {
        this.spaces = spaces;
    }

    public Set<GameEntity> getGames() {
        return games;
    }

    public void setGames(Set<GameEntity> games) {
        this.games = games;
    }

    public Board toModel() {
        return new Board(new Board.Id(id), spaces.stream().map(BoardSpaceEntity::toModel).toList());
    }


}
