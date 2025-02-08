package com.julien.plop.auth.presenter;


import com.julien.plop.StringTools;
import com.julien.plop.auth.persistence.AuthEntity;
import com.julien.plop.auth.persistence.AuthRepository;
import com.julien.plop.player.persistence.PlayerEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/auths")
public class AuthController {

    private final AuthRepository repository;

    public AuthController(AuthRepository repository) {
        this.repository = repository;
    }

    @PostMapping({"", "/"})
    public AuthResponseDTO save(@RequestBody AuthRequestDTO request) {
        try {
            AuthEntity entity = new AuthEntity();
            entity.setId(StringTools.generate());
            entity.setToken(generate());
            entity.setDeviceId(request.deviceId());
            if (request.playerId() != null) {
                PlayerEntity playerEntity = new PlayerEntity();
                playerEntity.setId(request.playerId());
                entity.setPlayer(playerEntity);
            }
            entity.setDateTime(Instant.now());
            repository.save(entity);
            return new AuthResponseDTO(entity.getToken());
        } catch (Exception e) {
            if(e instanceof EntityNotFoundException || e.getCause() instanceof EntityNotFoundException) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Player not found", e);
            }
            throw e;
        }
    }

    private String generate() {
        return UUID.randomUUID().toString();
    }

}
