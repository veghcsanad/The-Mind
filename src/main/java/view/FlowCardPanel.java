package main.java.view;

import main.java.model.Card;
import main.java.model.Game;

import java.awt.*;
import javax.swing.*;

public class FlowCardPanel extends JPanel {
    public static int CARD_WIDTH = 255;
    private FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,10,0);

    public FlowCardPanel(){
        setLayout(flowLayout);
    }

    private JButton addCardButton(Card card, Game game) {
        int btnWidth = 180;
        int btnHeight = 300;
        ImageIcon crdImg = new ImageIcon(card.getCardPicture());
        Image scaledCrdImg = crdImg.getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        JButton cardPicButton = new JButton(new ImageIcon(scaledCrdImg));
        cardPicButton.setPreferredSize(new Dimension(180, 300));
        /* cardPicButton.addActionListener(new CardDiscarder(game, card)); */
        add(cardPicButton);
        setBackground(new Color(0, 102, 51));
        revalidate();
        return cardPicButton;
    }

    public void setHand(Game game) {
        removeAll();
        for (Card card : game.getHumanPlayer().getHand()) {
            JButton cardButton = addCardButton(card, game);
        }
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
    }
}
