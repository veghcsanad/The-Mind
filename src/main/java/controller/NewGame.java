package main.java.controller;

import main.java.model.Game;
import main.java.view.GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A controller class for creating a new game.
 */
public class NewGame implements ActionListener {
    GameView modelGameView;
    Game modelGame;

    public NewGame(GameView modelGameView, Game modelGame) {
        this.modelGameView = modelGameView;
        this.modelGame = modelGame;
    }

    public void actionPerformed(ActionEvent e) {
        int decision = JOptionPane.showConfirmDialog(null,"Are you sure you want to start a new game? This will end your progress in the current game.");
        if (decision == 0) {
            modelGameView.dispose();
            modelGame = new Game();
            GameView gameView = new GameView(modelGame);
        }
    }
}