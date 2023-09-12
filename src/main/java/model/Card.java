package main.java.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Card {
    private int number;

    public Card(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public BufferedImage getCardPicture() {
        try {
            return (BufferedImage) ImageIO.read(new File("src/main/resources/cards/"+ this.toString() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
