package com.julien.plop.auth;

import com.julien.plop.player.domain.model.Player;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Auth {

    public static class Except extends Exception {

        public Except(String message) {
            super(message);
        }

    }

    private final static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public Player find(String rawToken) throws Except {
        String[] split = rawToken.split("\\.");
        OffsetDateTime dateTime = OffsetDateTime.parse(split[2], fmt);
        if(dateTime.isBefore(OffsetDateTime.now().plusHours(5))) {
            throw new Except("Token expired");
        }
        return new Player(new Player.Id(split[0]), split[1]);
    }

    public String generate(Player player) { //TODO Player name sans point
        return player.id().value() + "." + player.name() + "." + fmt.format(OffsetDateTime.now());
    }

}
