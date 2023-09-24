/* token: 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 */

package main.java;

import main.java.model.StartGame;
import main.java.view.GameView;
import main.java.view.NewGame;

public class Main {
    public static void main(String[] args) {
        StartGame startGame = new StartGame(3);
        NewGame newGame = new NewGame(startGame);
    }
}