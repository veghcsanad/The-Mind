package main.java.controller;

import main.java.model.Card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.model.Game;
public class CardDiscarder implements ActionListener {
    private Game modelGame;
    private Card modelCard;

    public CardDiscarder(Game modelGame, Card modelCard) {
        this.modelGame = modelGame;
        this.modelCard = modelCard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modelGame.playCard(modelGame.getHumanPlayer(), modelCard);
    }
}
