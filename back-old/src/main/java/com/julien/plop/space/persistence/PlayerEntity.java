package com.julien.plop.board.persistence;


import com.julien.plop.game.GameEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "test1_player")
public class PlayerEntity {

    @Id
    @UuidGenerator
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }
}
