package com.julien.plop.player.presenter;


import com.julien.plop.i18n.domain.Language;
import com.julien.plop.tools.StringTools;
import com.julien.plop.auth.domain.AuthException;
import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.player.persistence.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final AuthUseCase auth;

    private final PlayerRepository repository;

    public PlayerController(AuthUseCase auth, PlayerRepository repository) {
        this.auth = auth;
        this.repository = repository;
    }

    @PostMapping({"/", ""})
    public PlayerResponseDTO create(
            @RequestHeader("Authorization") String rawToken,
            @RequestBody PlayerRequestDTO request) {
        try {
            auth.verify(rawToken);
            PlayerEntity entity = new PlayerEntity();
            entity.setId(StringTools.generate());
            entity.setName(request.name());
            entity.setDeviceId(request.deviceId());
            entity.setLanguage(Language.valueOfSafe(request.language()));
            return PlayerResponseDTO.fromEntity(repository.save(entity));
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        }
    }

    @GetMapping({"/{playerId}/", "/{playerId}"})
    public PlayerResponseDTO findOne(@RequestHeader("Authorization") String rawToken,
                                     @PathVariable("playerId") String playerId) {
        try {
            auth.verify(rawToken);
            return PlayerResponseDTO.fromEntity(repository.findById(playerId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found")));
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        }

    }

}
