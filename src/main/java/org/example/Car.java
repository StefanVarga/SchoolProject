package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Car extends Entity implements KeyListener {
    int health = 3;

    public Car(int x, int y, int width, int height, String file) {
        super(x,y,width,height);
        this.width = 190;
        this.height = 60;
        setImage(file);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keys = e.getKeyCode();
        if (keys == KeyEvent.VK_W) {
            if(y == 270){
                y = 190;
            }
            if(y == 360){
                y = 270;
            }
        }

        if (keys == KeyEvent.VK_S) {
            if(y == 270){
                y = 360;
            }
            if(y == 190){
                y = 270;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}