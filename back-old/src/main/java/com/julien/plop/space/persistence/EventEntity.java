package com.julien.plop.board.persistence;


import com.julien.plop.game.GameEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Table(name = "test1_event")
public class EventEntity {

    @Id
    @UuidGenerator
    private String id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;


    /*
    @ManyToOne
    @JoinColumn(name = "space_id")
    private AbstractSpaceEntity space;*/






}
