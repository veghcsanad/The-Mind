package main.java.model;

import main.java.view.GameView;

import javax.swing.*;
import java.time.LocalDateTime;
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
    public boolean gameWon = false;
    public boolean gameLost = false;
    private boolean started = false;
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
        initializeDeck();
        this.humanPlayer = new Player(dealCards(numCards), true);
        this.computerPlayer = new Player(dealCards(numCards), false);
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
        notifyListeners();
    }

    public void computerPlayCard() {
        if (computerPlayer.hand.isEmpty()) {
            computerTimer.stop();
            return;
        }
        playCard(this.computerPlayer, this.computerPlayer.hand.get(0));
    }

    private int calculateDelay(int gapSize) {
        // Adjust these constants as needed for your game's pace
        final int BASE_DELAY = 1000; // 1-second base delay
        final int GAP_MULTIPLIER = 500; // 0.5 second per gap size

        return BASE_DELAY + (gapSize * GAP_MULTIPLIER);
    }

    public boolean isStarted() { return started; }
    public void start() {
        started = true;
        computerTimer.start();
    }

    public void winOrLose() {
        if (humanPlayer.hand.isEmpty() && computerPlayer.hand.isEmpty()) {
            computerTimer.stop();
            gameWon = true;
        }
        for (Card card : humanPlayer.hand) {
            if (card.getNumber() < last.getNumber()) {
                computerTimer.stop();
                gameLost = true;
            }
        }
        for (Card card : computerPlayer.hand) {
            if (card.getNumber() < last.getNumber()) {
                computerTimer.stop();
                gameLost = true;
            }
        }
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameLost() {
        return gameLost;
    }

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