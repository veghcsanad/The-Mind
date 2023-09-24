package main.java.model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputerTimer {
    private boolean stopped = false;
    private int elapsedTime = 0;
    private int gap = 0;
    private Game game;
    private final int WAITING_TIME = 500; // in milliseconds

    public ComputerTimer(Game game) {
        this.game = game;
    }

    Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += WAITING_TIME;
            if (elapsedTime == gap * WAITING_TIME) {
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
