package com.julien.plop.game.persistence;


import com.julien.plop.game.GameState;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_GAME")
public class GameEntity {

    @Id
    @UuidGenerator
    private String id;

    private String label;

    @Enumerated(EnumType.STRING)
    private GameState state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

}
