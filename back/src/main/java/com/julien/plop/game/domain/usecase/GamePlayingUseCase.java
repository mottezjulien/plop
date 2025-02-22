package com.julien.plop.game.domain.usecase;

import com.julien.plop.CacheKeyList;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.player.domain.model.Player;

import java.util.List;
import java.util.Optional;

public class GamePlayingUseCase {

    public interface DataOutputPort {

        Optional<Game> findById(Game.Id gameId);

        List<GamePlayer> findPlayers(Game game);

    }

    private final DataOutputPort data;
    private final CacheKeyList<Game, GamePlayer> cache;

    public GamePlayingUseCase(DataOutputPort data, CacheKeyList<Game, GamePlayer> cache) {
        this.data = data;
        this.cache = cache;
    }

    public GamePlayer apply(Game.Id gameId, Player.Id playerId) throws GameException {
        Optional<Game> optGame = cache.findFirstIf(game -> game.is(gameId));
        if(optGame.isPresent()) {
            Game game = optGame.get();
            List<GamePlayer> players = cache.get(game);
            return players.stream()
                    .filter(player -> player.is(playerId))
                    .findFirst()
                    .orElseThrow(() -> new GameException(GameException.Type.PLAYER_NOT_IN_GAME));
        }
        optGame = data.findById(gameId);
        if(optGame.isPresent()) {
            Game game = optGame.orElseThrow();
            if(!game.isPlaying()) {
                throw new GameException(GameException.Type.GAME_NOT_PLAYING);
            }
            List<GamePlayer> players = data.findPlayers(game);
            cache.put(game, players);
            return players.stream()
                    .filter(player -> player.is(playerId))
                    .findFirst()
                    .orElseThrow(() -> new GameException(GameException.Type.PLAYER_NOT_IN_GAME));
        }
        throw new GameException(GameException.Type.GAME_NOT_FOUND);

    }

}
