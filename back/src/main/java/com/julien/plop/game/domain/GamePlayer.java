package com.julien.plop.game.domain;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.player.domain.model.Player;

import java.util.Optional;

public class GamePlayer {

    private final Game.Id gameId;
    private final Player player;

    //TODO TO IMMUTABLE
    private Optional<BoardSpace.Point> optPosition;

    public GamePlayer(Game.Id gameId, Player player, Optional<BoardSpace.Point> optPosition) {
        this.gameId = gameId;
        this.player = player;
        this.optPosition = optPosition;
    }

    public Game.Id gameId() {
        return gameId;
    }

    public Player.Id playerId() {
        return player.id();
    }


    //TODO TO IMMUTABLE
    public void move(BoardSpace.Point point) {
        optPosition = Optional.of(point);
    }

    public Optional<BoardSpace.Point> position() {
        return optPosition;
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
     */

}
