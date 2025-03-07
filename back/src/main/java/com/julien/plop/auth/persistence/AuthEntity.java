package com.julien.plop.auth.persistence;


import com.julien.plop.auth.domain.Auth;
import com.julien.plop.player.persistence.PlayerEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "TEST1_AUTH")
public class AuthEntity {

    @Id
    //@UuidGenerator
    private String id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "date_time")
    private Instant dateTime;

    private String token;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public Auth toModel() {
        return new Auth(new Auth.Id(id), token, dateTime, deviceId,
                Optional.ofNullable(player).map(PlayerEntity::toModel));
    }

    public static AuthEntity fromModel(Auth auth) {
        AuthEntity entity = new AuthEntity();
        entity.setId(auth.id().value());
        entity.setToken(auth.rawToken());
        entity.setDateTime(auth.dateTime());
        entity.setDeviceId(auth.deviceId());
        auth.optPlayer().ifPresent(player -> entity.setPlayer(PlayerEntity.fromModel(player)));
        return entity;
    }

}
