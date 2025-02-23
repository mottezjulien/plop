package com.julien.plop.game.domain;


import com.julien.plop.board.model.Board;
import com.julien.plop.scenario.Scenario;
import com.julien.plop.template.domain.Template;

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

    public enum State {
        INIT, PLAYING, OVER, PAUSED
    }

    private final Atom atom;
    private final String templateVersion;
    private final Scenario scenario;
    private final Board board;
    private final State state;

    public Game(Id id, Template.Id templateId) {
        this(id, templateId, State.INIT);
    }

    public Game(Id id, Template.Id templateId, State state) {
        this(id, "any game label", templateId, "0.0.0", new Scenario(), new Board(), state);
    }

    public Game(Id id, String label, Template.Id templateId, String templateVersion, Scenario scenario, Board board) {
        this(id, label, templateId, templateVersion, scenario, board, State.INIT);
    }

    public Game(Id id, String label, Template.Id templateId, String templateVersion, Scenario scenario, Board board, State state) {
        this.atom = new Atom(id, templateId, label);
        this.templateVersion = templateVersion;
        this.scenario = scenario;
        this.board = board;
        this.state = state;
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

    public boolean isPlaying() {
        return state == State.PLAYING;
    }

    public boolean is(Id id) {
        return Objects.equals(id, this.atom.id());
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


