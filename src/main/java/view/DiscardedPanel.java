package main.java.view;

import javax.swing.*;
import java.awt.*;
import main.java.model.Card;

public class DiscardedPanel extends JPanel {
    private JLabel lastLabel;

    public DiscardedPanel() {
        setBackground(new Color(0,102,51));
        lastLabel = new JLabel();
        add(lastLabel);
    }

    public void update(Card last) {
        removeAll();
        if (last.getNumber() == 0) {
            SwingUtilities.updateComponentTreeUI(this);
            return;
        }
        lastLabel.setIcon(new ImageIcon(last.getCardPicture()));
        add(lastLabel);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
