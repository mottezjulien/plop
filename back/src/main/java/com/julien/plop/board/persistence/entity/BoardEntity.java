package com.julien.plop.board.persistence.entity;

import com.julien.plop.board.model.Board;
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
    //@UuidGenerator
    private String id;

    @OneToMany(mappedBy = "board")
    private Set<BoardSpaceEntity> spaces = new HashSet<>();


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

    public Board toModel() {
        return new Board(new Board.Id(id), spaces.stream().map(BoardSpaceEntity::toModel).toList());
    }


}
