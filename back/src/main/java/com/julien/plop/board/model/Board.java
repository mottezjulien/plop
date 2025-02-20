package com.julien.plop.board.model;

import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.tools.StringTools;

import java.util.List;
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
    //private final Map<Player.Id, List<BoardSpace.Point>> positionsByPlayer = new HashMap<>();

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

    public List<BoardSpace> spaces(GamePlayer inGamePlayer) {
        Optional<BoardSpace.Point> optPoint = inGamePlayer.position();
        return optPoint.stream()
                .flatMap(point -> spaces.stream().filter(space -> space.is(point)))
                .toList();
    }


    /*
    public List<BoardSpace> is(Player.Id playerId) {
        return point(playerId).stream().flatMap(point -> spaces.stream()
                .filter(space -> space.is(point)))
                .toList();
    }

    public Optional<BoardSpace.Point> point(Player.Id playerId) {
         return Optional.ofNullable(positionsByPlayer.get(playerId))
                .map(List::getLast);
    }

    public void move(Player.Id playerId, BoardSpace.Point point) {
        List<BoardSpace.Point> history = positionsByPlayer.getOrDefault(playerId, new ArrayList<>());
        history.add(point);
        positionsByPlayer.put(playerId, history);
    }*/

}
