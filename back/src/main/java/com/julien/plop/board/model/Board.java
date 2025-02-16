package com.julien.plop.board.model;

import com.julien.plop.History;
import com.julien.plop.StringTools;
import com.julien.plop.player.domain.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class Board {

    public record Id(String value) {
        public Id() {
            this(StringTools.generate());
        }
    }

    private final Id id;
    private final List<BoardSpace> spaces;
    private final Map<Player.Id, History<BoardSpace.Point>> historyPositionsByPlayer = new HashMap<>();

    public Board(Id id, List<BoardSpace> spaces) {
        this.id = id;
        this.spaces = spaces;
    }

    public Board(List<BoardSpace> spaces) {
        this(new Id(), spaces);
    }

    public Id id() {
        return id;
    }

    public Stream<BoardSpace> spaces() {
        return spaces.stream();
    }

    public List<BoardSpace> is(Player.Id playerId) {
        Optional<BoardSpace.Point> optPosition =
                Optional.ofNullable(historyPositionsByPlayer.get(playerId))
                        .map(History::last);
        return optPosition.stream().flatMap(point -> spaces.stream()
                .filter(space -> space.is(point)))
                .toList();
    }

    public void move(Player.Id playerId, BoardSpace.Point point) {
        History<BoardSpace.Point> history = historyPositionsByPlayer.getOrDefault(playerId, new History<>());
        history.add(point);
    }

}
