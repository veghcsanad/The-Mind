package main.java;

import main.java.model.Experiment;
import main.java.model.Game;
import main.java.model.TrainExp;
import main.java.view.GameView;
import main.java.view.TrainExpView;

public class Main {
    public static void main(String[] args) {
        TrainExpView trainExpView = new TrainExpView(new TrainExp());
        //GameView gameView = new GameView(new Game());
    }
}