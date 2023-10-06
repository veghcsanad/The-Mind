package main.java.model;

import main.java.view.GameView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Experiment {
    ArrayList<ArrayList<Card>> participantCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> computerCards = new ArrayList<>();

    public Experiment() {
        initLists();
        for (int i = 0; i < participantCards.size(); i++) {
            Game experimentGame = new Game(participantCards.get(i), computerCards.get(i));
            GameView gameView = new GameView(experimentGame);
            while(!experimentGame.isNextRound()) {
                continue;
            }
            gameView.setVisible(false);
        }
        System.exit(0);
    }

    private void initLists() {
        ArrayList<Card> handP = new ArrayList<>(Arrays.asList(new Card(17), new Card(52), new Card(81)));
        ArrayList<Card> handC = new ArrayList<>(Arrays.asList(new Card(25), new Card(27), new Card(87)));
        participantCards.add(handP);
        computerCards.add(handC);

        handP = new ArrayList<>(Arrays.asList(new Card(4), new Card(34), new Card(63)));
        handC = new ArrayList<>(Arrays.asList(new Card(19), new Card(24), new Card(70)));
        participantCards.add(handP);
        computerCards.add(handC);

        handP = new ArrayList<>(Arrays.asList(new Card(33), new Card(70), new Card(99)));
        handC = new ArrayList<>(Arrays.asList(new Card(10), new Card(54), new Card(100)));
        participantCards.add(handP);
        computerCards.add(handC);
    }
}
