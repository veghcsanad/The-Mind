package main.java.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Card> deck;
    private int numCards;
    private Player humanPlayer;
    private Player computerPlayer;

    public Player getHumanPlayer(){
        return humanPlayer;
    }

    public Game(int numCards) {
        initializeDeck();
        this.numCards = numCards;
        this.humanPlayer = new Player(dealCards(numCards), true);
        this.computerPlayer = new Player(dealCards(numCards), false);
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
            if (deck.isEmpty()) {
                break;
            }
            Card card = deck.remove(0);
            hand.add(card);
        }
        return hand;
    }

    public void playCard(Card card) {
        // Implement logic to check if the card is played in ascending order
        // Update game state accordingly (e.g., track rounds, lives, etc.)
    }

    public boolean isGameWon() {
        // Implement logic to check if the game is won (all cards played in order)
        return false;
    }

    public boolean isGameLost() {
        // Implement logic to check if the game is lost (e.g., no more lives)
        return false;
    }
}