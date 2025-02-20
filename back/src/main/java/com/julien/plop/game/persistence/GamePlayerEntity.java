package com.julien.plop.game.persistence;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.player.persistence.PlayerEntity;
import com.julien.plop.tools.StringTools;
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

    @MapsId("gameId")
    @ManyToOne
    @JoinColumn(name = "game_id")
    public GameEntity game;

    @MapsId("playerId")
    @ManyToOne
    @JoinColumn(name = "player_id")
    public PlayerEntity player;

    @OneToMany(mappedBy = "gamePlayer")
    private Set<GamePlayerActionEntity> actions = new HashSet<>();

    public GamePlayerId getId() {
        return id;
    }

    public void setId(GamePlayerId id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Set<GamePlayerActionEntity> getActions() {
        return actions;
    }

    public void setActions(Set<GamePlayerActionEntity> actions) {
        this.actions = actions;
    }


    public GamePlayer toModel() {
        Game.Id gameId = new Game.Id(id.gameId());
        Player playerModel = player.toModel();
        Optional<GamePlayerActionEntity> opt = lastMove();
        Optional<BoardSpace.Point> optPosition = opt.map(action -> (BoardSpace.Point)action.value().orElse(null));
        return new GamePlayer(gameId, playerModel, optPosition);
    }

    private Optional<GamePlayerActionEntity> lastMove() {
        return actions.stream()
                .filter(action -> action.getType() == GamePlayerActionEntity.Type.MOVE)
                .max(Comparator.comparing(GamePlayerActionEntity::getDate));
    }

}
