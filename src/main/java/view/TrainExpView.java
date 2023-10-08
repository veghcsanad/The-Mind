package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import main.java.controller.NextBtnCtr;
import main.java.model.Card;
import main.java.model.Experiment;
import main.java.model.TrainExp;


public class TrainExpView extends JFrame implements PropertyChangeListener {
    private TrainExp trainExp;
    private boolean ended = false;
    private JPanel panel = new JPanel();
    private JButton btn;

    public TrainExpView(TrainExp trainExp) {
        this.trainExp = trainExp;
        init();
        revalidate();
    }

    private void init() {
        setUndecorated(true);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("The Mind - Instructions");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        this.getContentPane().setBackground(new Color(240,240,240));
        setLocationRelativeTo(null);
        updatePanels();
        this.add(panel, BorderLayout.NORTH);
        JPanel btnPanel = new JPanel();
        this.btn = new JButton("Next");
        btn.setFont(new Font(btn.getFont().getName(), btn.getFont().getStyle(), 20));
        btn.addActionListener(new NextBtnCtr(trainExp));
        this.add(new JLabel(""), BorderLayout.CENTER);
        btnPanel.add(btn);
        this.add(btnPanel, BorderLayout.CENTER);
        revalidate();
        setVisible(true);
        trainExp.addListener(this);
    }

    public void updatePanels() {
        switch (trainExp.getStage()) {
            case 1:
                testPrep();
                break;
            case 2:
                test1Panel();
                break;
            case 3:
                test2Panel();
                break;
            case 4:
                endTestPanel();
                break;
            case 5:
                ended = true;
                this.setVisible(false);
                Experiment experiment = new Experiment();
                experiment.nextRound();
                dispose();
            default:
                instructionPanel();
        }
    }

    public void instructionPanel() {
        this.panel.removeAll();
        JLabel instructionText = new JLabel();
        instructionText.setText("<html>" +
                "<center>" +
                "    <b><h2>Instructions</h2></b>" +
                "</center>" +
                "<p> This is a game of \"The Mind\".<p>" +
                "<p> In this game you will have to collaborate" +
                "<p> with the computer to win. You will both get" +
                "<p> a number of cards in your hands. You do not" +
                "<p> have any information about the cards in the hand" +
                "<p> of the computer and the computer does not know" +
                "<p> your cards either.<p>" +
                "<p> Without knowing each other's cards," +
                "<p> you will have to put down the cards" +
                "<p> in your hands in ascending order.<p>" +
                "<p> The cards in the deck are numbered 1-100.<p>" +
                "<p> Good luck!" +
                "</html>");
        instructionText.setFont(new Font(instructionText.getFont().getName(), instructionText.getFont().getStyle(), 18));
        instructionText.setForeground(new Color(0,0,0));
        panel.add(instructionText);
        revalidate();
    }

    private void testPrep() {
        this.panel.removeAll();
        JLabel instructionText = new JLabel();
        instructionText.setText("<html>" +
                "<center>" +
                "    <b><h2>Test game</h2></b>" +
                "</center>" +
                "<p> Now you are going to play a test game.<p>" +
                "<p> In this game, you and the computer will get" +
                "<p> 1 card each. <p>" +
                "<p> Try to play your card in the right time." +
                "<p> The computer will also play its card when" +
                "<p> it feels reasonable. <p>" +
                "<p> Good luck!" +
                "</html>");
        instructionText.setFont(new Font(instructionText.getFont().getName(), instructionText.getFont().getStyle(), 18));
        instructionText.setForeground(new Color(0,0,0));
        panel.add(instructionText);
        revalidate();
    }

    private void test1Panel() {
        this.setVisible(false);
        trainExp.newTest(new ArrayList<Card>(Arrays.asList(new Card(5))), new ArrayList<Card>(Arrays.asList(new Card(82))));
    }

    private void test2Panel() {
        trainExp.closeView();
        trainExp.newTest(new ArrayList<Card>(Arrays.asList(new Card(77))), new ArrayList<Card>(Arrays.asList(new Card(10))));
    }

    private void endTestPanel() {
        trainExp.closeView();
        this.setVisible(true);
        this.panel.removeAll();
        JLabel instructionText = new JLabel();
        instructionText.setText("<html>" +
                "<center>" +
                "    <b><h2>Starting the experiment...</h2></b>" +
                "</center>" +
                "<p> Now you should be ready to play the game.<p>" +
                "<p> There is going to be 10 trials where you and" +
                "<p> the computer will have 3 cards each. <p>" +
                "<p> Good luck!" +
                "</html>");
        instructionText.setFont(new Font(instructionText.getFont().getName(), instructionText.getFont().getStyle(), 18));
        instructionText.setForeground(new Color(0,0,0));
        panel.add(instructionText);
        btn.setText("Start experiment!");
        revalidate();
    }

    public boolean isEnded() {
        return ended;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updatePanels();
    }
}