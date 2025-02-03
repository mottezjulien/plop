package com.julien.plop.auth.domain;

import com.julien.plop.player.domain.model.Player;

import java.time.OffsetDateTime;
import java.util.Optional;

public class Auth {

    private final OffsetDateTime dateTime;

    private final Optional<Player> optPlayer;

    public Auth(OffsetDateTime dateTime, Optional<Player> optPlayer) {
        this.dateTime = dateTime;
        this.optPlayer = optPlayer;
    }

    public boolean isExpiry() {
        return dateTime.isAfter(OffsetDateTime.now().plusHours(1));
    }

    public Player player() throws AuthException {
        return optPlayer.orElseThrow(() -> new AuthException(AuthException.Type.ANONYMOUS));
    }

}

/*
public class Auth123 {

    private final static DateTimeFormatter fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public Player find(String rawToken) throws AuthException {
        String[] split = rawToken.split("___");
        OffsetDateTime dateTime = OffsetDateTime.parse(split[2], fmt);
        if(dateTime.isAfter(OffsetDateTime.now().plusHours(5))) {
            throw new AuthException(AuthException.Type.EXPIRED_TOKEN);
        }
        return new Player(new Player.Id(split[0]), split[1]);
    }

    public String generate(Player player) { //TODO Player name sans _ ?? sans . ??
        return player.id().value() + "___" + player.name() + "___" + fmt.format(OffsetDateTime.now());
    }

}*/
