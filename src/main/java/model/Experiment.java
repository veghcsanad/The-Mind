package main.java.model;

import main.java.view.GameView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Experiment {
    ArrayList<ArrayList<Card>> participantCards = new ArrayList<>();
    ArrayList<ArrayList<Card>> computerCards = new ArrayList<>();
    private int round = 0;
    public final int MAX_ROUND = 8;
    private GameView gameView;
    private DataRecorder dataRecorder = new DataRecorder();

    private ArrayList<ArrayList<ArrayList<Card>>> expRounds = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(new Card(4), new Card(34), new Card(63))),
                    new ArrayList<>(Arrays.asList(new Card(11), new Card(52), new Card(81))),
                    new ArrayList<>(Arrays.asList(new Card(13), new Card(16), new Card(45))),
                    new ArrayList<>(Arrays.asList(new Card(33), new Card(70), new Card(99))))),
            new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(new Card(22), new Card(24), new Card(70))),
                    new ArrayList<>(Arrays.asList(new Card(25), new Card(27), new Card(87))),
                    new ArrayList<>(Arrays.asList(new Card(1), new Card(5), new Card(68))),
                    new ArrayList<>(Arrays.asList(new Card(10), new Card(54), new Card(100)))))));

    private ArrayList<ArrayList<ArrayList<Card>>> nonExpRounds = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(new Card(28), new Card(60), new Card(93))),
                    new ArrayList<>(Arrays.asList(new Card(37), new Card(39), new Card(96))),
                    new ArrayList<>(Arrays.asList(new Card(26), new Card(37), new Card(90))),
                    new ArrayList<>(Arrays.asList(new Card(24), new Card(29), new Card(71))))),
            new ArrayList<>(Arrays.asList(
                    new ArrayList<>(Arrays.asList(new Card(14), new Card(53), new Card(81))),
                    new ArrayList<>(Arrays.asList(new Card(24), new Card(73), new Card(75))),
                    new ArrayList<>(Arrays.asList(new Card(49), new Card(62), new Card(69))),
                    new ArrayList<>(Arrays.asList(new Card(2), new Card(58), new Card(76)))))));

    private Random random = new Random();

    public void nextRound() {
        round = round + 1;
        if (gameView != null) {
            gameView.dispose();
        }
        ArrayList<Card> handP = null;
        ArrayList<Card> handC = null;
        int computerStrategy = -1;
        int randomInt;
        switch(round) {
            case 1: // NON-EXP
                randomInt = random.nextInt(4);
                handP = nonExpRounds.get(0).get(randomInt);
                handC = nonExpRounds.get(1).get(randomInt);
                nonExpRounds.get(0).remove(randomInt);
                nonExpRounds.get(1).remove(randomInt);
                computerStrategy = 0;
                break;
            case 2: // EXP
                randomInt = random.nextInt(4);
                handP = expRounds.get(0).get(randomInt);
                handC = expRounds.get(1).get(randomInt);
                expRounds.get(0).remove(randomInt);
                expRounds.get(1).remove(randomInt);
                if (handC.get(2).getNumber() == 100) {
                    computerStrategy = 2;
                } else {
                    computerStrategy = 1;
                }
                break;
            case 3: // EXP
                randomInt = random.nextInt(3);
                handP = expRounds.get(0).get(randomInt);
                handC = expRounds.get(1).get(randomInt);
                expRounds.get(0).remove(randomInt);
                expRounds.get(1).remove(randomInt);
                if (handC.get(2).getNumber() == 100) {
                    computerStrategy = 2;
                } else {
                    computerStrategy = 1;
                }
                break;
            case 4: // NON-EXP
                randomInt = random.nextInt(3);
                handP = nonExpRounds.get(0).get(randomInt);
                handC = nonExpRounds.get(1).get(randomInt);
                nonExpRounds.get(0).remove(randomInt);
                nonExpRounds.get(1).remove(randomInt);
                computerStrategy = 0;
                break;
            case 5: // EXP
                randomInt = random.nextInt(2);
                handP = expRounds.get(0).get(randomInt);
                handC = expRounds.get(1).get(randomInt);
                expRounds.get(0).remove(randomInt);
                expRounds.get(1).remove(randomInt);
                if (handC.get(2).getNumber() == 100) {
                    computerStrategy = 2;
                } else {
                    computerStrategy = 1;
                }
                break;
            case 6: // NON-EXP
                randomInt = random.nextInt(2);
                handP = nonExpRounds.get(0).get(randomInt);
                handC = nonExpRounds.get(1).get(randomInt);
                nonExpRounds.get(0).remove(randomInt);
                nonExpRounds.get(1).remove(randomInt);
                computerStrategy = 0;
                break;
            case 7: // EXP
                handP = expRounds.get(0).get(0);
                handC = expRounds.get(1).get(0);
                expRounds.get(0).remove(0);
                expRounds.get(1).remove(0);
                if (handC.get(2).getNumber() == 100) {
                    computerStrategy = 2;
                } else {
                    computerStrategy = 1;
                }
                break;
            case 8: // NON-EXP
                handP = nonExpRounds.get(0).get(0);
                handC = nonExpRounds.get(1).get(0);
                nonExpRounds.get(0).remove(0);
                nonExpRounds.get(1).remove(0);
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
