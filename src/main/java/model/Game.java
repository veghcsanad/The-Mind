package main.java.model;

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
    public boolean gameWon;
    public boolean gameLost;

    public Player getHumanPlayer(){
        return humanPlayer;
    }
    public Player getComputerPlayer(){
        return computerPlayer;
    }

    public Game(int numCards) {
        this.numCards = numCards;
        this.gameLost = false;
        this.gameWon = false;
        initializeDeck();
        this.humanPlayer = new Player(dealCards(numCards), true);
        this.computerPlayer = new Player(dealCards(numCards), false);
    }

    public void newGame(int numCards) {
        initializeDeck();
        this.humanPlayer.hand = dealCards(numCards);
        this.computerPlayer.hand = dealCards(numCards);
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
        if (isGameLost()) {
            gameLost = true;
        } else if (isGameWon()) {
            gameWon = true;
        }
        notifyListeners();
    }

    public boolean isGameWon() {
        if (humanPlayer.hand.isEmpty() && computerPlayer.hand.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isGameLost() {
        for (Card card : humanPlayer.hand) {
            if (card.getNumber() < last.getNumber()) {
                return true;
            }
        }
        for (Card card : computerPlayer.hand) {
            if (card.getNumber() < last.getNumber()) {
                return true;
            }
        }
        return false;
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