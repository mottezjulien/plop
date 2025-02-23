package com.julien.plop.auth.adapter;

import com.julien.plop.auth.domain.Auth;
import com.julien.plop.auth.domain.AuthUseCase;
import com.julien.plop.auth.persistence.AuthEntity;
import com.julien.plop.auth.persistence.AuthRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthAdapter implements AuthUseCase.OutPort {

    private final AuthRepository repository;

    public AuthAdapter(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Auth> findByToken(String rawToken) {
        return repository.findByTokenFetchPlayer(rawToken).map(AuthEntity::toModel);
    }


}
