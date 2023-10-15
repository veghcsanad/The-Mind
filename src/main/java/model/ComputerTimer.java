package main.java.model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputerTimer {
    private boolean stopped = false;
    private int elapsedTime = 0;
    private int gap = 0;
    private Game game;
    private final int WAITING_TIME = 300; // in milliseconds

    public ComputerTimer(Game game) {
        this.game = game;
    }

    Timer timer = new Timer(WAITING_TIME, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += WAITING_TIME;
            boolean playCard;
            if (game.getHumanPlayer().hand.isEmpty()) {
                playCard = true;
            } else {
                switch (game.getComputerStrategy()) {
                    case 1:
                        playCard = ((elapsedTime == (gap * WAITING_TIME)*2) &&
                                game.getComputerPlayer().hand.size() == 1) || ((elapsedTime == gap * WAITING_TIME) &&
                                game.getComputerPlayer().hand.size() > 1);
                        break;
                    case 2:
                        playCard = ((game.getComputerPlayer().hand.size() != 1) &&
                                (elapsedTime == gap * WAITING_TIME));
                        break;
                    default:
                        playCard = (elapsedTime == gap * WAITING_TIME);
                }
            }
            if (playCard) {
                game.computerPlayCard();
            }
        }
    });

    public void start() {
        if (stopped) {
            return;
        }
        if (game.isGameWon() || game.isGameLost() || game.getComputerPlayer().hand.isEmpty()) {
            return;
        }
        this.elapsedTime = 0;
        this.gap = game.getComputerPlayer().hand.get(0).getNumber() - game.getLast().getNumber();
        timer.start();
    }

    public void restart() {
        if (stopped) {
            return;
        }
        if (game.isGameWon() || game.isGameLost() || game.getComputerPlayer().hand.isEmpty()) {
            return;
        }
        this.elapsedTime = 0;
        this.gap = game.getComputerPlayer().hand.get(0).getNumber() - game.getLast().getNumber();
        timer.restart();
    }

    public void stop() {
        this.stopped = true;
        timer.stop();
    }
}
