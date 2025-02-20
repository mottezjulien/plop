package com.julien.plop.game.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.tools.StringTools;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "TEST1_GAME_PLAYER_ACTION")
public class GamePlayerActionEntity {

    public enum Type {
        INIT, MOVE;

        Optional<Class> valueClass() {
            switch (this) {
                case MOVE:
                    return Optional.of(BoardSpace.Point.class);
                default:
                    return Optional.empty();
            }
        }

    }


    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "game_player_id")
    private GamePlayerEntity gamePlayer;

    @Column(name = "json_values", columnDefinition = "TEXT")
    private String jsonValues;

    private Instant date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public GamePlayerEntity getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayerEntity gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public String getJsonValues() {
        return jsonValues;
    }

    public void setJsonValues(String jsonValues) {
        this.jsonValues = jsonValues;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Optional<Object> value() {
        if (StringTools.isFilled(jsonValues)) {
            Optional<Class> clazz = type.valueClass();
            if (clazz.isPresent()) {
                try {
                    return Optional.of(StringTools.fromJson(jsonValues, clazz.orElseThrow()));
                } catch (JsonProcessingException ignored) {

                }
            }
        }
        return Optional.empty();
    }

}
