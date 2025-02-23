package com.julien.plop.player.presenter;


import com.julien.plop.auth.domain.Auth;
import com.julien.plop.auth.domain.AuthException;
import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.auth.persistence.AuthEntity;
import com.julien.plop.auth.persistence.AuthRepository;
import com.julien.plop.i18n.domain.Language;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.player.persistence.PlayerRepository;
import com.julien.plop.tools.StringTools;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final AuthUseCase authUseCase;

    private final PlayerRepository repository;

    private final AuthRepository authRepository;

    public PlayerController(AuthUseCase authUseCase, PlayerRepository repository, AuthRepository authRepository) {
        this.authUseCase = authUseCase;
        this.repository = repository;
        this.authRepository = authRepository;
    }

    @PostMapping({"/", ""})
    public PlayerResponseDTO create(
            @RequestHeader("Authorization") String rawToken,
            @RequestBody PlayerRequestDTO request) {
        try {
            Auth auth = authUseCase.verify(rawToken);
            PlayerEntity entity = new PlayerEntity();
            entity.setId(StringTools.generate());
            entity.setName(request.name());
            entity.setLanguage(Language.valueOfSafe(request.language()));
            entity = repository.save(entity);
            auth = auth.withPlayer(entity.toModel());
            authRepository.save(AuthEntity.fromModel(auth));
            return PlayerResponseDTO.fromEntity(entity);
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        }
    }

    @GetMapping({"/me/", "/me"})
    public PlayerResponseDTO findMe(@RequestHeader("Authorization") String rawToken) {
        try {
            Auth auth = authUseCase.verify(rawToken);

            return PlayerResponseDTO.fromModel(auth.optPlayer()
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found")));
        } catch (AuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getType().name(), e);
        }

    }

}
