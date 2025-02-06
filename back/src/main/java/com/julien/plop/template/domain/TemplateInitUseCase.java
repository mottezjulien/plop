package com.julien.plop.template.domain;

import com.julien.plop.I18n;
import com.julien.plop.Language;
import com.julien.plop.board.model.Board;
import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.generic.AndOrOr;
import com.julien.plop.scenario.Possibility;
import com.julien.plop.scenario.PossibilityCondition;
import com.julien.plop.scenario.PossibilityConsequence;
import com.julien.plop.scenario.PossibilityTrigger;
import com.julien.plop.scenario.Scenario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TemplateInitUseCase {

    public interface OutPort {

        boolean isEmpty();

        void createAll(Template template);

    }

    private final OutPort outPort;

    public TemplateInitUseCase(OutPort outPort) {
        this.outPort = outPort;
    }

    public void apply() {
        if (outPort.isEmpty()) {
            outPort.createAll(firstTemplate());
        }
    }

    private Template firstTemplate() {
        Board board = firstBoard();
        Scenario scenario = firstScenario(board);
        return new Template(Template.generateId(), "test-template", "Mon premier jeu", "0.0.1",
                scenario, board);
    }

    private Scenario firstScenario(Board board) {

        Scenario.Step.Id stepId = new Scenario.Step.Id();

        I18n rdvGare = new I18n("RDV à la gare desc",
                Map.of(Language.FR, "RDV à la gare en français", Language.EN, "RDV à la gare en anglais"));
        Scenario.Target target1 = new Scenario.Target(Optional.of(rdvGare), Optional.empty(), false);

        I18n goPiscine = new I18n("Go piscine desc",
                Map.of(Language.FR, "Aller à la piscine en français", Language.EN, "Aller à la piscine en anglais"));
        I18n goPiscineDesc = new I18n("Ton bonnet desc",
                Map.of(Language.FR, "Et n'oublie pas ton bonnet en français", Language.EN, "Et n'oublie pas ton bonnet en anglais"));
        Scenario.Target target2 = new Scenario.Target(Optional.of(goPiscine), Optional.of(goPiscineDesc), true);

        PossibilityTrigger trigger1 = new PossibilityTrigger.GoOutSpace(new PossibilityTrigger.Id(), board.spaces().getFirst().id());
        List<PossibilityCondition> conditions1 = List.of();

        PossibilityConsequence consequence1 = new PossibilityConsequence.EndedStep(new PossibilityConsequence.Id(), stepId);
        List<PossibilityConsequence> consequences1 = List.of(consequence1);
        Possibility possibility1 = new Possibility(trigger1, conditions1, AndOrOr.AND, consequences1);

        List<Scenario.Target> targets1 = List.of(target1, target2);
        List<Possibility> possibilities1 = List.of(possibility1);

        I18n firstChapter = new I18n("Premier chapitre desc",
                Map.of(Language.FR, "Premier Chapitre FR", Language.EN, "Premier Chapitre EN"));
        Scenario.Step step1 = new Scenario.Step(stepId, Optional.of(firstChapter), targets1, possibilities1);
        List<Scenario.Step> steps = List.of(step1);
        return new Scenario("Mon premier scénario", steps);
    }

    private Board firstBoard() {
        BoardSpace.Rect rect = new BoardSpace.Rect(
                new BoardSpace.Point(5.098765f, 60.980f),
                new BoardSpace.Point(1.0f, 6.098760f));
        List<BoardSpace> spaces = List.of(new BoardSpace("La gare", 1, List.of(rect)));
        return new Board(spaces);
    }

}
