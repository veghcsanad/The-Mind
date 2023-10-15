package main.java.model;

import main.java.view.GameView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Experiment {
    ArrayList<ArrayList<Card>> participantCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> computerCards = new ArrayList<>();
    private int round = 0;
    public final int MAX_ROUND = 10;
    private GameView gameView;
    private DataRecorder dataRecorder = new DataRecorder();

    public void nextRound() {
        round = round + 1;
        if (gameView != null) {
            gameView.dispose();
        }
        ArrayList<Card> handP = null;
        ArrayList<Card> handC = null;
        int computerStrategy = -1;
        switch(round) {
            case 1:
                handP = new ArrayList<>(Arrays.asList(new Card(28), new Card(60), new Card(93)));
                handC = new ArrayList<>(Arrays.asList(new Card(14), new Card(53), new Card(81)));
                computerStrategy = 0;
                break;
            case 2:
                handP = new ArrayList<>(Arrays.asList(new Card(4), new Card(34), new Card(63)));
                handC = new ArrayList<>(Arrays.asList(new Card(19), new Card(24), new Card(70)));
                computerStrategy = 1;
                break;
            case 3:
                handP = new ArrayList<>(Arrays.asList(new Card(17), new Card(52), new Card(81)));
                handC = new ArrayList<>(Arrays.asList(new Card(25), new Card(27), new Card(87)));
                computerStrategy = 1;
                break;
            case 4:
                handP = new ArrayList<>(Arrays.asList(new Card(4), new Card(73), new Card(75)));
                handC = new ArrayList<>(Arrays.asList(new Card(17), new Card(19), new Card(96)));
                computerStrategy = 0;
                break;
            case 5:
                handP = new ArrayList<>(Arrays.asList(new Card(13), new Card(16), new Card(45)));
                handC = new ArrayList<>(Arrays.asList(new Card(2), new Card(5), new Card(68)));
                computerStrategy = 1;
                break;
            case 6:
                handP = new ArrayList<>(Arrays.asList(new Card(49), new Card(62), new Card(69)));
                handC = new ArrayList<>(Arrays.asList(new Card(26), new Card(37), new Card(90)));
                computerStrategy = 0;
                break;
            case 7:
                handP = new ArrayList<>(Arrays.asList(new Card(31), new Card(40), new Card(56)));
                handC = new ArrayList<>(Arrays.asList(new Card(9), new Card(22), new Card(76)));
                computerStrategy = 0;
                break;
            case 8:
                handP = new ArrayList<>(Arrays.asList(new Card(2), new Card(17), new Card(79)));
                handC = new ArrayList<>(Arrays.asList(new Card(52), new Card(53), new Card(88)));
                computerStrategy = 0;
                break;
            case 9:
                handP = new ArrayList<>(Arrays.asList(new Card(33), new Card(70), new Card(99)));
                handC = new ArrayList<>(Arrays.asList(new Card(10), new Card(54), new Card(100)));
                computerStrategy = 2;
                break;
            case 10:
                handP = new ArrayList<>(Arrays.asList(new Card(24), new Card(29), new Card(71)));
                handC = new ArrayList<>(Arrays.asList(new Card(2), new Card(58), new Card(76)));
                computerStrategy = 0;
                break;
            default:
                return;
        }
        Game game = new Game(handP, handC, this, computerStrategy);
        gameView = new GameView(game);
        dataRecorder.startNewGame(round, handP, handC);
    }

    public int getRound() { return round; }

    public DataRecorder getDataRecorder() { return dataRecorder; }
}
