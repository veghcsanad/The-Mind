package main.java.model;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private boolean isHuman;

    public Player(ArrayList<Card> hand, boolean isHuman){
        this.hand = hand;
        this.isHuman = isHuman;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }
}
