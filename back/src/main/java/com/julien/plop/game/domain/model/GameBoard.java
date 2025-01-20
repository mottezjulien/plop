package com.julien.plop.game.domain.model;

import java.util.List;

public record GameBoard(GameId gameId, List<GameBoardSpace> spaces) {

}
