package com.julien.plop.board.persistence;

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
}
