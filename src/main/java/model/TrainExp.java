package main.java.model;

import main.java.controller.NextBtnCtr;
import main.java.view.GameView;
import main.java.view.TrainExpView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TrainExp {
    Collection<PropertyChangeListener> listeners = new ArrayList<>();
    private int stage = 0;
    private Game test;
    private GameView gameView;

    public void newTest(ArrayList<Card> handP, ArrayList<Card> handC) {
        this.gameView = new GameView(new Game(handP, handC, true, this));
    }

    public void nextStage() {
        stage = stage + 1;
        notifyListeners();
    }

    public int getStage() {
        return stage;
    }

    public void closeView() {
        this.gameView.setVisible(false);
        this.gameView.dispose();
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        PropertyChangeEvent payload = new PropertyChangeEvent(this, "game", null, null);
        for (PropertyChangeListener listener : listeners) {
            listener.propertyChange(payload);
        }
    }

    public void previousStage() {
        if (stage == 4) {
            stage = 1;
        } else {
            stage = stage - 1;
        }
        notifyListeners();
    }
}
