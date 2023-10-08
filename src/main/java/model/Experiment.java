package main.java.model;

import main.java.view.GameView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Experiment {
    ArrayList<ArrayList<Card>> participantCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> computerCards = new ArrayList<>();
    private int round = 0;
    private GameView gameView;

    public void nextRound() {
        round = round + 1;
        if (gameView != null) {
            gameView.dispose();
        }
        ArrayList<Card> handP = null;
        ArrayList<Card> handC = null;
        switch(round) {
            case 1:
                handP = new ArrayList<>(Arrays.asList(new Card(17), new Card(52), new Card(81)));
                handC = new ArrayList<>(Arrays.asList(new Card(25), new Card(27), new Card(87)));
                break;
            case 2:
                handP = new ArrayList<>(Arrays.asList(new Card(4), new Card(34), new Card(63)));
                handC = new ArrayList<>(Arrays.asList(new Card(19), new Card(24), new Card(70)));
                break;
            case 3:
                handP = new ArrayList<>(Arrays.asList(new Card(33), new Card(70), new Card(99)));
                handC = new ArrayList<>(Arrays.asList(new Card(10), new Card(54), new Card(100)));
                break;
            default:
                return;
        }
        gameView = new GameView(new Game(handP, handC, this));
    }

    public int getRound() { return round; }
}
