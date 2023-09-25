package main.java.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GiveUpper implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int decision = JOptionPane.showConfirmDialog(null,"Are you sure you want to give up?");
        if (decision == 0) {
            System.out.println("Congrats on being a quitter...");
            System.exit(0);
        }
    }
}
