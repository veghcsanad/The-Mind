package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.java.controller.GiveInstructions;
import main.java.controller.GiveUpper;
import main.java.controller.NewGame;
import main.java.model.Game;


public class GameView extends JFrame implements PropertyChangeListener {
    private Game game;
    private FlowCardPanel flowCardPanelHuman;
    private FlowCardPanel flowCardPanelComputer;
    private DiscardedPanel discardedPanel;

    public GameView(Game game) {
        this.game = game;
        this.flowCardPanelHuman = new FlowCardPanel(game);
        this.flowCardPanelComputer = new FlowCardPanel(game);
        this.discardedPanel = new DiscardedPanel(game, this);
        init();
        initMenuBar();
        revalidate();
    }

    public void setupPanels(Game game) {
        add(flowCardPanelHuman, BorderLayout.SOUTH);
        add(flowCardPanelComputer, BorderLayout.NORTH);
        flowCardPanelHuman.setHand(game.getHumanPlayer());
        flowCardPanelComputer.setHand(game.getComputerPlayer());
        add(discardedPanel, BorderLayout.CENTER);
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

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("<html><h2>Menu</h2></html>");
        JMenuItem giveUpItem = new JMenuItem("Give up!");
        giveUpItem.addActionListener(new GiveUpper());
        JMenuItem newGameItem = new JMenuItem("New game");
        newGameItem.addActionListener(new NewGame(this, game));
        JMenuItem instructionsItem = new JMenuItem("Instructions");
        instructionsItem.addActionListener(new GiveInstructions());
        menu.add(giveUpItem);
        menu.add(newGameItem);
        menu.add(instructionsItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public void updatePanels() {
        flowCardPanelComputer.setHand(game.getComputerPlayer());
        flowCardPanelHuman.setHand(game.getHumanPlayer());
        discardedPanel.update(game.getLast());
    }

    public void updateToEnd() {
        this.flowCardPanelComputer.removeAll();
        this.flowCardPanelHuman.removeAll();
        if (game.isExperiment()) {
            this.discardedPanel.updateToEndExp();
        } else {
            this.discardedPanel.updateToEnd();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updatePanels();
        if(game.isGameWon() || game.isGameLost()) {
            updateToEnd();
        }
    }
}