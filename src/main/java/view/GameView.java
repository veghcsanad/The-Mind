package main.java.view;

import javax.swing.*;
import java.awt.*;
import main.java.model.Game;


public class GameView extends JFrame {
    private Game game;
    private FlowCardPanel flowCardPanel = new FlowCardPanel();


    public GameView(Game game) {
        this.game = game;
        init();
        revalidate();
    }

    public void setupPanels(Game game) {
        add(flowCardPanel, BorderLayout.SOUTH);
        flowCardPanel.setHand(game);
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

}