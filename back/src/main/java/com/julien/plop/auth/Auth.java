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

    //private final static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final static DateTimeFormatter fmt = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public Player find(String rawToken) throws Except {
        String[] split = rawToken.split("___");
        OffsetDateTime dateTime = OffsetDateTime.parse(split[2], fmt);
        if(dateTime.isAfter(OffsetDateTime.now().plusHours(5))) {
            throw new Except("Token expired");
        }
        return new Player(new Player.Id(split[0]), split[1]);
    }

    public String generate(Player player) { //TODO Player name sans point
        return player.id().value() + "___" + player.name() + "___" + fmt.format(OffsetDateTime.now());
    }

}
