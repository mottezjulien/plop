package com.julien.plop.auth.presenter;


import com.julien.plop.auth.persistence.AuthEntity;
import com.julien.plop.auth.persistence.AuthRepository;
import com.julien.plop.player.persistence.PlayerEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthRepository repository;

    public AuthController(AuthRepository repository) {
        this.repository = repository;
    }

    @PostMapping({"", "/"})
    public AuthResponseDTO save(@RequestBody AuthRequestDTO request) {
        AuthEntity entity = new AuthEntity();
        entity.setToken(generate());
        entity.setDeviceId(request.deviceId());
        if (request.playerId() != null) {
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setId(request.playerId());
            entity.setPlayer(playerEntity);
        }
        entity.setDateTime(OffsetDateTime.now());
        repository.save(entity);
        return new AuthResponseDTO(entity.getToken());
    }

    private String generate() {
        return UUID.randomUUID().toString();
    }

}
