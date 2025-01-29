package com.julien.plop.board.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_BOARD_SPACE")
public class BoardSpaceEntity {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @OneToMany(mappedBy = "space")
    private Set<BoardRectEntity> rects = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public void setBoard(BoardEntity board) {
        this.board = board;
    }

    public Set<BoardRectEntity> getRects() {
        return rects;
    }

    public void setRects(Set<BoardRectEntity> rects) {
        this.rects = rects;
    }

}
