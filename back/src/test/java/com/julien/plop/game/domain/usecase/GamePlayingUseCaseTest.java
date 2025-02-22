package com.julien.plop.game.domain.usecase;

import com.julien.plop.CacheKeyList;
import com.julien.plop.game.domain.Game;
import com.julien.plop.game.domain.GameException;
import com.julien.plop.game.domain.GamePlayer;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.template.domain.Template;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GamePlayingUseCaseTest {

    private final Game.Id gameId = new Game.Id("gameId");
    private final Player.Id playerId = new Player.Id("playerId");

    private final DataInMemory data = new DataInMemory();
    private final CacheKeyList<Game, GamePlayer> cache = new CacheKeyList<>();
    private final GamePlayingUseCase useCase = new GamePlayingUseCase(data, cache);

    @BeforeEach
    void setUp() {
        data.clear();
        cache.clear();
        //when(cacheRepository.findByGameId(gameId)).thenReturn(Optional.empty());

    }

    @Test
    public void throwExceptionWhenGameNotFound() {
        assertThatThrownBy(() -> useCase.apply(gameId, playerId))
                .isInstanceOf(GameException.class)
                .hasFieldOrPropertyWithValue("type", GameException.Type.GAME_NOT_FOUND);
    }

    @Test
    public void throwExceptionWhenPlayerNotInGame() {

        createDataGame(Game.State.PLAYING);

        assertThatThrownBy(() -> useCase.apply(gameId, playerId))
                .isInstanceOf(GameException.class)
                .hasFieldOrPropertyWithValue("type", GameException.Type.PLAYER_NOT_IN_GAME);

    }



    @Test
    public void returnPlayerInGameFound_putInCache_callCache() throws GameException {

        createDataGame(Game.State.PLAYING);

        createDataGamePlayer();

        GamePlayer gamingPlayer = useCase.apply(gameId, playerId);
        assertThat(gamingPlayer.gameId()).isEqualTo(gameId);
        assertThat(gamingPlayer.playerId()).isEqualTo(playerId);

        //verify(cacheRepository).addPlayer(gameId, gamingPlayer);


        /*




        //Game game = new Game(gameId, new Template.Id("anyTemplateId"));
        //cacheRepository.addGame(game);

        Player player = new Player("Bob");
        GamePlayer playingPlayer =  new GamePlayer(gameId, player);
        cacheRepository.addPlayer(gameId, playingPlayer);

        GamePlayer gamingPlayer = useCase.apply(gameId, playerId);


*/
    }

    plop


    private void createDataGame(Game.State state) {
        data.createGame(new Game(gameId, new Template.Id("anyTemplateId"), state));
    }

    private void createDataGamePlayer() {
        data.createGamePlayer(new GamePlayer(gameId, playerId));
    }

}

class DataInMemory implements GamePlayingUseCase.DataOutputPort {

    private final List<Game> games = new ArrayList<>();

    private final List<GamePlayer> gamePlayers = new ArrayList<>();

    public void createGame(Game game) {
        games.add(game);
    }

    public void createGamePlayer(GamePlayer gamePlayer) {
        gamePlayers.add(gamePlayer);
    }

    @Override
    public Optional<Game> findById(Game.Id gameId) {
        return games.stream()
                .filter(game -> game.is(gameId))
                .findFirst();
    }

    @Override
    public List<GamePlayer> findPlayers(Game game) {
        return gamePlayers.stream()
                .filter(gamePlayer -> game.is(gamePlayer.gameId()))
                .toList();
    }

    public void clear() {
        games.clear();
        gamePlayers.clear();
    }
}