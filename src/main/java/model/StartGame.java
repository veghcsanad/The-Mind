package main.java.model;

import main.java.view.GameView;

public class StartGame {
    private int numOfCards;
    public StartGame(int numOfCards){
        this.numOfCards = numOfCards;
    }
    public void start() {
        Game game = new Game(numOfCards);
        GameView gameView = new GameView(game);
    }
}
