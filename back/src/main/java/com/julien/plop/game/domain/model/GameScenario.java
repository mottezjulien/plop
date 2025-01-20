package com.julien.plop.game.domain.model;

import java.util.List;

public record GameScenario(GameId gameId, List<GameScenarioStep> steps) {

}
