package com.julien.plop.game;

//TODO Un jeu -> un plateau, un scénario, des joueurs, des états de jeux par joueur, bref, il faut découper
//TODO "Game", GameScenario, GameBoard, GamePlaying


import com.julien.plop.board.model.Board;
import com.julien.plop.player.domain.model.Player;
import com.julien.plop.scenario.Scenario;
import com.julien.plop.template.Template;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Game {

    public record Id(String value) {
        public static Id generate() {
            return new Id(UUID.randomUUID().toString());
        }
    }

    public record Atom(Id id, Template.Id templateId, String label) {

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Atom atom)) return false;
            return Objects.equals(id, atom.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

    }

    private final Atom atom;
    private final String templateVersion;
    private final Scenario scenario;
    private final Board board;
    private final List<Player> players = new ArrayList<>();

    public Game(Id id, Template.Id templateId, String label, String templateVersion, Scenario scenario, Board board) {
        this.atom = new Atom(id, templateId, label);
        this.templateVersion = templateVersion;
        this.scenario = scenario;
        this.board = board;
    }

    public Id id() {
        return atom.id();
    }

    public String label() {
        return atom.label();
    }

    public Template.Id templateId() {
        return atom.templateId();
    }

    public String templateVersion() {
        return templateVersion;
    }

    public Scenario scenario() {
        return scenario;
    }

    public Board board() {
        return board;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Game game)) return false;
        return atom.equals(game.atom);
    }

    @Override
    public int hashCode() {
        return atom.hashCode();
    }
}


