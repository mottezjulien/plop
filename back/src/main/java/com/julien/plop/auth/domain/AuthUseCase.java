package com.julien.plop.auth.domain;

import com.julien.plop.player.domain.model.Player;

import java.util.Optional;

public class AuthUseCase {


    public interface OutPort {
        Optional<Auth> findByToken(String rawToken);

    }

    private final OutPort port;

    public AuthUseCase(OutPort port) {
        this.port = port;
    }

    public Auth verify(String rawToken) throws AuthException {
        Optional<Auth> opt = port.findByToken(rawToken);
        Auth auth = opt.orElseThrow(() -> new AuthException(AuthException.Type.EMPTY));
        if (auth.isExpiry()) {
            throw new AuthException(AuthException.Type.EXPIRED_TOKEN);
        }
        return auth;
    }

    public Player find(String rawToken) throws AuthException {
        return verify(rawToken).player();
    }


}
