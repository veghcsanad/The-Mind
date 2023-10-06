package main.java.view;

import javax.swing.*;
import java.awt.*;

import main.java.controller.GameStarter;
import main.java.model.Card;
import main.java.model.Game;

public class DiscardedPanel extends JPanel {
    private JLabel lastLabel;
    private Game game;
    private GameView gameView;

    public DiscardedPanel(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        setBackground(new Color(13,118,34));
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(new GameStarter(game, gameView));
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
        JLabel cardsHuman = new JLabel("Your cards were: " + game.getHumanPlayer().ogHand);
        JLabel cardsComputer = new JLabel("The computer's cards were: " + game.getComputerPlayer().ogHand);
        cardsHuman.setFont(new Font(cardsHuman.getFont().getName(), cardsHuman.getFont().getStyle(), 24));
        cardsHuman.setForeground(new Color(255,255,255));
        cardsComputer.setFont(new Font(cardsComputer.getFont().getName(), cardsComputer.getFont().getStyle(), 24));
        cardsComputer.setForeground(new Color(255,255,255));
        JLabel goodbyeMsg = new JLabel();
        if (game.isGameWon()) {
            goodbyeMsg.setText("You won this round! Congratulations!");
        } else {
            goodbyeMsg.setText("You lost this round! Better luck next time!");
        }
        goodbyeMsg.setFont(new Font(goodbyeMsg.getFont().getName(), goodbyeMsg.getFont().getStyle(), 24));
        goodbyeMsg.setForeground(new Color(255,255,255));
        add(goodbyeMsg);
        add(cardsHuman);
        add(cardsComputer);

        JButton nextRound = new JButton("Next Round");
        nextRound.addActionListener(e -> {
            game.goNextRound();
        });
        add(nextRound);
    }
}
