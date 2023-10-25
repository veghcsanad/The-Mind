package main.java.model;

import javax.swing.*;
import java.util.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Game {
    Collection<PropertyChangeListener> listeners = new ArrayList<>();
    private List<Card> deck;
    private Card last = new Card(0);
    private int numCards;
    private Player humanPlayer;
    private Player computerPlayer;
    private int computerStrategy = -1;
    public boolean gameWon = false;
    public boolean gameLost = false;
    private boolean started = false;
    private boolean nextRound = false;
    private boolean isExperiment;
    private Experiment experiment = null;
    private boolean train = false;
    private TrainExp trainExp = null;
    private ComputerTimer computerTimer = new ComputerTimer(this);

    public Player getHumanPlayer(){
        return humanPlayer;
    }
    public Player getComputerPlayer(){
        return computerPlayer;
    }

    public Game() {
        introGame();
        this.gameLost = false;
        this.gameWon = false;
        this.isExperiment = false;
        initializeDeck();
        this.humanPlayer = new Player(dealCards(numCards), true);
        this.computerPlayer = new Player(dealCards(numCards), false);
    }

    public Game(ArrayList<Card> handP, ArrayList<Card> handC, Experiment experiment, int computerStrategy) {
        this.gameLost = false;
        this.gameWon = false;
        this.isExperiment = true;
        this.experiment = experiment;
        this.humanPlayer = new Player(handP, true);
        this.computerPlayer = new Player(handC, false);
        this.computerStrategy = computerStrategy;
    }

    public Game(ArrayList<Card> handP, ArrayList<Card> handC, boolean isTrain, TrainExp trainExp) {
        this.gameLost = false;
        this.gameWon = false;
        this.isExperiment = true;
        this.train = true;
        this.trainExp = trainExp;
        this.humanPlayer = new Player(handP, true);
        this.computerPlayer = new Player(handC, false);
    }

    public void introGame() {
        JOptionPane.showMessageDialog(null, "Hello! This is The Mind! A game where you have fun!\nYou will find the instructions in the menu bar. Let's get started!");
        Object[] options = {"1", "2", "3", "4", "5", "6"};
        this.numCards = JOptionPane.showOptionDialog(null,"How many cards do you want to play with?","Start new game!", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,null, options, options[2]) + 1;
    }

    private void initializeDeck() {
        deck = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            deck.add(new Card(i));
        }
        Collections.shuffle(deck); // Shuffle the deck before starting the game
    }

    public ArrayList<Card> dealCards(int numCards) {
        ArrayList<Card> hand = new ArrayList<Card>(numCards);
        for (int i = 0; i < numCards; i++) {
            if (this.deck.isEmpty()) {
                break;
            }
            Card card = deck.remove(0);
            hand.add(card);
        }
        Collections.sort(hand, cardComparator);
        notifyListeners();
        return hand;
    }

    public Card getLast() {
        return last;
    }

    public void playCard(Player player, Card card) {
        this.last = card;
        player.hand.remove(card);
        winOrLose();
        computerTimer.restart();
        if(isExperiment && !train) {
            experiment.getDataRecorder().cardPlayed(player, card);
        }
        notifyListeners();
    }

    public void computerPlayCard() {
        if (computerPlayer.hand.isEmpty()) {
            computerTimer.stop();
            return;
        }
        playCard(this.computerPlayer, this.computerPlayer.hand.get(0));
    }

    public boolean isStarted() { return started; }

    public void start() {
        started = true;
        computerTimer.start();
        if (isExperiment && !isTrain()) {
            experiment.getDataRecorder().resetTime();
        }
    }

    public void winOrLose() {
        if (humanPlayer.hand.isEmpty() && computerPlayer.hand.isEmpty()) {
            computerTimer.stop();
            gameWon = true;
        }
        if ((!humanPlayer.hand.isEmpty()) && (humanPlayer.hand.get(0).getNumber() < last.getNumber())) {
            computerTimer.stop();
            gameLost = true;
        }
        if ((!computerPlayer.hand.isEmpty()) && (computerPlayer.hand.get(0).getNumber() < last.getNumber())) {
            computerTimer.stop();
            gameLost = true;
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isNextRound() {
        return nextRound;
    }

    public boolean isExperiment() {
        return isExperiment;
    }

    public Experiment getExperiment() { return experiment; }

    public int getComputerStrategy() { return computerStrategy; }

    public boolean isTrain() { return train; }

    public TrainExp getTrainExp() { return trainExp; }

    public Comparator<Card> cardComparator = new Comparator<Card>() {
        @Override
        public int compare(Card a, Card b) {
            // Compare based on number
            return Integer.compare(a.getNumber(), b.getNumber());
        }
    };

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        PropertyChangeEvent payload = new PropertyChangeEvent(this, "game", null, null);
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(payload);
        }
    }
}