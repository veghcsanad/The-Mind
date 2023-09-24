package main.java.view;

import main.java.controller.CardDiscarder;
import main.java.model.Card;
import main.java.model.Game;
import main.java.model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlowCardPanel extends JPanel {
    private Game game;
    private FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER,10,0);
    public FlowCardPanel(Game game) {
        this.game = game;
        setLayout(flowLayout);
    }

    private JButton addCardButton(Card card) {
        int btnWidth = 180;
        int btnHeight = 300;
        ImageIcon crdImg = new ImageIcon(card.getCardPicture());
        Image scaledCrdImg = crdImg.getImage().getScaledInstance(btnWidth, btnHeight, Image.SCALE_SMOOTH);
        JButton cardPicButton = new JButton(new ImageIcon(scaledCrdImg));
        cardPicButton.setPreferredSize(new Dimension(btnWidth, btnHeight));
        cardPicButton.addActionListener(new CardDiscarder(game, card));
        add(cardPicButton);
        setBackground(new Color(0, 102, 51));
        revalidate();
        return cardPicButton;
    }

    private void addEmptyCard() {
        int imgWidth = 135;
        int imgHeight = 225;
        try {
            BufferedImage img = ImageIO.read(new File("src/main/resources/cards/empty.png"));
            ImageIcon crdImg = new ImageIcon(img);
            Image scaledCrdImg = crdImg.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            JLabel emptyCard = new JLabel(new ImageIcon(scaledCrdImg));
            add(emptyCard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(new Color(0, 102, 51));
    }

    public void setHand(Player player) {
        removeAll();
        if (!player.getIsHuman()) {
            for (Card card : player.hand) {
                addEmptyCard();
            }
        } else {
            for (Card card : player.hand) {
                addCardButton(card);
            }
        }
        SwingUtilities.updateComponentTreeUI(this);
        revalidate();
    }
}
