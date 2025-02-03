package com.julien.plop.player.persistence;

import com.julien.plop.Language;
import com.julien.plop.player.domain.model.Player;
import jakarta.persistence.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "TEST1_PLAYER")
public class PlayerEntity {

    @Id
    //@UuidGenerator
    private String id;

    private String name;

    @Column(name = "device_id")
    private String deviceId;

    @Enumerated(STRING)
    private Language language;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Player toModel() {
        return new Player(new Player.Id(id), name);
    }
}
