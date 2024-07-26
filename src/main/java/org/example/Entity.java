package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity extends Coordinates {
    public int width, height;
    private BufferedImage img;

    public Entity(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void setImage(String file) {
        try {
            img = ImageIO.read(getClass().getResource("/" + file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }
    public BufferedImage getImg() {
        return img;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

