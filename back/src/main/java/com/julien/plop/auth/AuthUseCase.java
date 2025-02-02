package com.julien.plop.auth;

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

    public Player find(String rawToken) throws AuthException {
        Optional<Auth> opt = port.findByToken(rawToken);
        Auth auth = opt.orElseThrow(() -> new AuthException(AuthException.Type.EMPTY));
        if (auth.isExpiry()) {
            throw new AuthException(AuthException.Type.EXPIRED_TOKEN);
        }
        /**/
        return auth.player();

    }
}
