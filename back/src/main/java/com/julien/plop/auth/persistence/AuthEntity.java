package com.julien.plop.auth.persistence;


import com.julien.plop.player.persistence.PlayerEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "TEST1_AUTH")
public class AuthEntity {

    @Id
    //@UuidGenerator
    private String id;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "date_time")
    private OffsetDateTime dateTime;

    private String token;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OffsetDateTime getDateTime() {
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
}
