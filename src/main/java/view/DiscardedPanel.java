package main.java.view;

import javax.swing.*;
import java.awt.*;

import main.java.controller.GameStarter;
import main.java.controller.NextBtnCtr;
import main.java.model.Card;
import main.java.model.Game;

public class DiscardedPanel extends JPanel {
    private JLabel lastLabel;
    private Game game;
    private GameView gameView;

    public DiscardedPanel(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        setBackground(new Color(0, 102, 51));
        if (game.isExperiment() && !game.isTrain()) {
            JLabel roundCount = new JLabel("Round " + String.valueOf(game.getExperiment().getRound()));
            roundCount.setFont(new Font(roundCount.getFont().getName(), roundCount.getFont().getStyle(), 24));
            roundCount.setForeground(new Color(255,255,255));
            add(roundCount);
        }
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(new GameStarter(game, gameView));
        startButton.setFont(new Font(startButton.getFont().getName(), startButton.getFont().getStyle(), 20));
        add(startButton);
    }

    public void update(Card last) {
        removeAll();
        setBackground(new Color(0, 102, 51));
        lastLabel = new JLabel();
        add(lastLabel);
        if (last.getNumber() == 0) {
            SwingUtilities.updateComponentTreeUI(this);
            return;
        }
        lastLabel.setIcon(new ImageIcon(last.getCardPicture()));
        add(lastLabel);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void updateToEnd() {
        JLabel cardsHuman = new JLabel("Your cards were: " + game.getHumanPlayer().ogHand);
        JLabel cardsComputer = new JLabel("The computer's cards were: " + game.getComputerPlayer().ogHand);
        cardsHuman.setFont(new Font(cardsHuman.getFont().getName(), cardsHuman.getFont().getStyle(), 24));
        cardsHuman.setForeground(new Color(255,255,255));
        cardsComputer.setFont(new Font(cardsComputer.getFont().getName(), cardsComputer.getFont().getStyle(), 24));
        cardsComputer.setForeground(new Color(255,255,255));
        JLabel goodbyeMsg = new JLabel();
        if (game.isGameWon()) {
            goodbyeMsg.setText("You won! Congratulations! Goodbye!");
        } else {
            goodbyeMsg.setText("You lost! Too bad... Goodbye!");
        }
        goodbyeMsg.setFont(new Font(goodbyeMsg.getFont().getName(), goodbyeMsg.getFont().getStyle(), 24));
        goodbyeMsg.setForeground(new Color(255,255,255));
        add(goodbyeMsg);
        add(cardsHuman);
        add(cardsComputer);
    }

    public void updateToEndExp() {
        removeAll();
        JLabel cardsHuman = new JLabel("Your cards were: " + game.getHumanPlayer().ogHand);
        JLabel cardsComputer = new JLabel("The computer's cards were: " + game.getComputerPlayer().ogHand);
        cardsHuman.setFont(new Font(cardsHuman.getFont().getName(), cardsHuman.getFont().getStyle(), 24));
        cardsHuman.setForeground(new Color(255,255,255));
        cardsComputer.setFont(new Font(cardsComputer.getFont().getName(), cardsComputer.getFont().getStyle(), 24));
        cardsComputer.setForeground(new Color(255,255,255));
        JLabel goodbyeMsg = new JLabel();
        if (game.isGameWon()) {
            goodbyeMsg.setText(
                    "<html>" +
                    "<center>" +
                    "    <b><h2>WON</h2></b>" +
                    "</center>" +
                    "<p> You completed this round without" +
                    "<p> any mistake." +
                    "</html>");
        } else {
            if ((!game.getComputerPlayer().hand.isEmpty()) &&
                    (game.getComputerPlayer().hand.get(0).getNumber() < game.getLast().getNumber())) {
                goodbyeMsg.setText(
                        "<html>" +
                        "<center>" +
                        "    <b><h2>LOST</h2></b>" +
                        "</center>" +
                        "<p> There was a mistake." +
                        "<p> You played " + game.getLast().getNumber() + " but the" +
                        "<p> computer had " + game.getComputerPlayer().hand.get(0) + "." +
                        "</html>");
            } else if (!game.getHumanPlayer().hand.isEmpty()) {
                goodbyeMsg.setText(
                        "<html>" +
                        "<center>" +
                        "    <b><h2>LOST</h2></b>" +
                        "</center>" +
                        "<p> There was a mistake." +
                        "<p> The computer played " + game.getLast().getNumber() + "but you had" +
                        "<p>  " + game.getHumanPlayer().hand.get(0) + " in your hand." +
                        "</html>");
            } else {
                goodbyeMsg.setText("There was an error.");
            }
        }
        goodbyeMsg.setFont(new Font(goodbyeMsg.getFont().getName(), goodbyeMsg.getFont().getStyle(), 24));
        goodbyeMsg.setForeground(new Color(255,255,255));
        add(cardsHuman);
        add(cardsComputer);
        add(goodbyeMsg);
    }
}
