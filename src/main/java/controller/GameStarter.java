package main.java.controller;

import main.java.model.Card;
import main.java.model.Game;
import main.java.model.StartGame;
import main.java.view.NewGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameStarter implements ActionListener {
    private final StartGame modelStartGame;
    private final NewGame modelNewGame;
    public GameStarter(StartGame modelStartGame, NewGame modelNewGame) {
        this.modelStartGame = modelStartGame;
        this.modelNewGame = modelNewGame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        modelStartGame.start();
        modelNewGame.setVisible(false);
    }
}
