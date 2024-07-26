package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameGraphics extends JFrame {
    private GameLogic logic;
    private Draw draw;
    private BufferedImage menu, gameOver, gameRules;

    public GameGraphics(GameLogic logic) throws HeadlessException {
        this.logic = logic;
        this.draw = new Draw();


        setSize(800, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Racing Rivals");
        setResizable(false);
        addKeyListener(logic.car);
        addKeyListener(logic);

        add(draw);
        try {
            menu = ImageIO.read(getClass().getResource("/menu.jpg"));
            gameOver = ImageIO.read(getClass().getResource("/gameOver.jpg"));
            gameRules = ImageIO.read(getClass().getResource("/rules.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(GameLogic logic) {
        this.logic = logic;
        repaint();
    }
    public class Draw extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("NFS Font", Font.BOLD, 30));
            g.setColor(Color.white);

            if(logic.gameActive == 0) {
                g.drawImage(menu, 0, 0, 800, 500, null);
                if(logic.gameRules) {
                    g.drawImage(gameRules, 0, 0, 800, 500, null);
                }
            }
            if (logic.gameActive == 1) {
                for (Object object : logic.backgrounds) {
                    g.drawImage(object.getImg(), object.x, object.y, object.getWidth(), object.getHeight(), null);
                }
                for(Object object: logic.hearts) {
                    g.drawImage(object.getImg(), object.x, object.y, object.getWidth(), object.getHeight(), null);
                }
                g.drawImage(logic.car.getImg(), logic.car.x, logic.car.y, logic.car.getWidth(), logic.car.getHeight(), null);
                for (Object object : logic.points) {
                    g.drawImage(object.getImg(), object.x, object.y, object.getWidth(), object.getHeight(), null);
                }
                for (Object object : logic.barriers) {
                    g.drawImage(object.getImg(), object.x, object.y, object.getWidth(), object.getHeight(), null);
                }
                g.drawString("Health   " + logic.car.health+" /3", 550, 30);
            }
            if(logic.gameActive == 2) {
                g.drawImage(gameOver, 0, 0, 800, 500, null);
            }
            g.drawString("Score   " + logic.score, 10, 30);
        }
    }
}
