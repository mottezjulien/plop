package com.julien.plop.player.persistence;

import com.julien.plop.i18n.domain.Language;
import com.julien.plop.player.domain.model.Player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "TEST1_PLAYER")
public class PlayerEntity {

    @Id
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
