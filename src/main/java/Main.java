/* token: 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 */

package main.java;

import main.java.model.Game;
import main.java.view.GameView;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(3);
        GameView gameView = new GameView(game);
    }
}