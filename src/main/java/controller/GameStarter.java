package main.java.controller;

import main.java.model.Game;
import main.java.view.DiscardedPanel;
import main.java.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStarter implements ActionListener {
    private Game modelGame;
    private GameView modelGameView;
    public GameStarter(Game modelGame, GameView modelGameView) {
        this.modelGame = modelGame;
        this.modelGameView = modelGameView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        modelGame.start();
        modelGameView.updatePanels();
    }
}
