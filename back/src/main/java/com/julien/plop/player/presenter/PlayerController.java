package com.julien.plop.player.presenter;


import com.julien.plop.Language;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.player.persistence.PlayerRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/")
    public PlayerResponseDTO create(@RequestBody PlayerRequestDTO request) {
        PlayerEntity entity = new PlayerEntity();
        entity.setName(request.name());
        entity.setDeviceId(request.deviceId());
        entity.setLanguage(Language.valueOf(request.language()));
        return PlayerResponseDTO.fromEntity(repository.save(entity));
    }

}
