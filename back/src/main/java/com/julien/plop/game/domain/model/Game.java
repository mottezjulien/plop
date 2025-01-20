package com.julien.plop.game.domain.model;

// Un jeu -> un plateau, un scénario, des joueurs, des états de jeux par joueur, bref, il faut découper
// "Game", GameScenario, GameBoard, GamePlaying

public record Game(
        GameId id,
        GameTemplateId templateId,
        String label) {

}


