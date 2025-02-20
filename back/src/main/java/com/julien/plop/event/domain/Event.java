package com.julien.plop.event.domain;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;

public record Event(Type type, Meta meta) {


    public enum Type {
        GO_IN_SPACE, GO_OUT_SPACE
    }

    public Player.Id playerId() {
        return meta.playerId();
    }

    public BoardSpace.Id spaceId() {
        return meta.spaceId();
    }

    public static class Meta {

        public enum Key {
            PLAYER_ID, SPACE_ID
        }

        private final Map<Key, Object> map = new HashMap<>();

        public void put(Key key, Object value) {
            switch (key) {
                case PLAYER_ID:
                    if (!(value instanceof Player.Id)) {
                        throw new IllegalArgumentException("The value must be a Player Id");
                    }
                    break;
                case SPACE_ID:
                    if (!(value instanceof BoardSpace.Id)) {
                        throw new IllegalArgumentException("The value must be a Board Id");
                    }
                    break;
            }
            map.put(key, value);
        }

        public Player.Id playerId() {
            return (Player.Id) map.get(Key.PLAYER_ID);
        }

        public BoardSpace.Id spaceId() {
            return (BoardSpace.Id) map.get(Key.SPACE_ID);
        }

    }


}
