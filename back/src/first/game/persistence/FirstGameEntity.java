package com.julien.plop.first.game.persistence;


import com.julien.plop.first.game.FirstGameState;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_GAME")
public class FirstGameEntity {

    @Id
    //@UuidGenerator
    private String id;

    private String label;

    @Enumerated(EnumType.STRING)
    private FirstGameState state;

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

    public FirstGameState getState() {
        return state;
    }

    public void setState(FirstGameState state) {
        this.state = state;
    }

}
