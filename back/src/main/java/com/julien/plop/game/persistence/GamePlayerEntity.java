package com.julien.plop.game.persistence;


import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.player.persistence.PlayerEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TEST1_GAME_PLAYER")
public class GamePlayerEntity {


    @Embeddable
    public record GamePlayerId(String gameId, String playerId) {

    }

    @Id
    private GamePlayerId id;

    @OneToMany(mappedBy = "gamePlayer")
    private Set<GamePlayerActionEntity> actions = new HashSet<>();

    public GamePlayerId getId() {
        return id;
    }

    public void setId(GamePlayerId id) {
        this.id = id;
    }

    public Set<GamePlayerActionEntity> getActions() {
        return actions;
    }

    public void setActions(Set<GamePlayerActionEntity> actions) {
        this.actions = actions;
    }


    public GamePlayer toModel() {
        Game.Id gameId = new Game.Id(id.gameId());
        Player.Id playerId = new Player.Id(id.playerId());
        Optional<GamePlayerActionEntity> opt = lastMove();
        Optional<BoardSpace.Point> optPosition = opt.map(action -> (BoardSpace.Point) action.value().orElse(null));
        return new GamePlayer(gameId, playerId, optPosition);
    }

    private Optional<GamePlayerActionEntity> lastMove() {
        return actions.stream()
                .filter(action -> action.getType() == GamePlayerActionEntity.Type.MOVE)
                .max(Comparator.comparing(GamePlayerActionEntity::getDate));
    }

}
