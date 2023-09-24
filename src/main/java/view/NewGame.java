package main.java.view;

import main.java.controller.CardDiscarder;
import main.java.controller.GameStarter;
import main.java.model.StartGame;

import javax.swing.*;

public class NewGame extends JPopupMenu {
    private final StartGame startGame;
    public NewGame(StartGame startGame) {
        this.startGame = startGame;
        setLabel("New game");
        setPopupSize(350, 450);
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(new GameStarter(startGame, this));
        add(startButton);
        revalidate();
        setVisible(true);
    }
}
