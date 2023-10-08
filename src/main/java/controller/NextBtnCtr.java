package main.java.controller;

import main.java.model.TrainExp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextBtnCtr implements ActionListener {
    private TrainExp trainExp;

    public NextBtnCtr(TrainExp modelTrainExp) {
        this.trainExp = modelTrainExp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        trainExp.nextStage();
    }
}
