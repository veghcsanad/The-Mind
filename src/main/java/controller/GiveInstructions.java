package main.java.controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A controller class for showing instructions.
 */
public class GiveInstructions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog( null, "<html>" +
                "<center>" +
                "    <b><h2>Instructions</h2></b>" +
                "</center>" +
                "<p>You are playing a game of Crazy Eights!" +
                "<p>To take your turn, either put down a valid card or draw a card." +
                "<p>A card is <b>valid</b> when it has a matching rank or suit to the card in the centre." +
                "<p> When it is not your turn, you can follow the moves of your opponents by pressing the 'Next player' button." +
                "<p> First player to have an <b>empty hand</b> wins!" +
                "<p>Now, some <i>special rules</i>:" +
                "<ol>" +
                "    <li>When an <b>Ace</b> is discarded, the order of turns is reversed.</li>" +
                "    <li>When someone puts down a <b>Two</b>, the next player has to draw two cards. </li>" +
                "    <li><b>Eights</b> are crazy in this game. They are always valid cards. Discarding one allows the player to choose a new current suit. </li>" +
                "    <li> When a player puts down a <b>Queen</b>, the next player's turn is skipped.</li>" +
                "</ol>" +
                "<center>" +
                "<p> <h2><i>Enjoy!</i></h2>" +
                "</center>" +
                "</html>");
    }
}
