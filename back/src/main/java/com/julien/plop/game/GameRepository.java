package com.julien.plop.game;

import java.util.ArrayList;
import java.util.List;

public interface GameRepository {

    void save(Game game);

    class InMemory implements GameRepository {

        private final List<Game> games = new ArrayList<>();

        @Override
        public void save(Game game) {
            games.removeIf(g -> g.equals(game));
            games.add(game);
        }
    }

}
