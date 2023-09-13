package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.java.model.Game;


public class GameView extends JFrame implements PropertyChangeListener {
    private Game game;
    private FlowCardPanel flowCardPanelHuman;
    private FlowCardPanel flowCardPanelComputer;
    private DiscardedPanel discardedPanel = new DiscardedPanel();


    public GameView(Game game) {
        this.game = game;
        this.flowCardPanelHuman = new FlowCardPanel(game);
        this.flowCardPanelComputer = new FlowCardPanel(game);
        init();
        revalidate();
    }

    public void setupPanels(Game game) {
        add(flowCardPanelHuman, BorderLayout.SOUTH);
        add(flowCardPanelComputer, BorderLayout.NORTH);
        flowCardPanelHuman.setHand(game.getHumanPlayer());
        flowCardPanelComputer.setHand(game.getComputerPlayer());
        add(discardedPanel, BorderLayout.CENTER);
        discardedPanel.update(game.getLast());
        game.addListener(this);
    }

    private void init() {
        setTitle("The Mind");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        this.getContentPane().setBackground(new Color(0,102,51));
        setLocationRelativeTo(null);
        setupPanels(game);
        revalidate();
        setVisible(true);
    }

    private void updatePanels() {
        flowCardPanelComputer.setHand(game.getComputerPlayer());
        flowCardPanelHuman.setHand(game.getHumanPlayer());
        discardedPanel.update(game.getLast());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updatePanels();
    }

}