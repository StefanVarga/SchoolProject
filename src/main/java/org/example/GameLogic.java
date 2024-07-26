package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GameLogic implements KeyListener {
    public ArrayList<Object> backgrounds;
    public ArrayList<Object> barriers, barriersRemove;
    public ArrayList<Object> points, pointsRemove;
    public ArrayList<Object> hearts, heartsRemove;
    public Car car;
    int gameActive = 0;
    boolean gameRules;
    int barrierTimer, barrierInterval = 100;
    int pointTimer, pointInterval = 250;
    int hearthTimer, hearthInterval = 1000;
    int score = 0;
    Random random;

    GameLogic() {

        initialize();
    }
    public void initialize(){
        backgrounds = new ArrayList<>();
        backgrounds.add(new Object(0, 0, 800, 500, "bg.jpg"));
        backgrounds.add(new Object(800, 0, 800, 500, "bg.jpg"));
        car = new Car(20, 190, 190, 60, "car.png");
        random = new Random();
        barriers = new ArrayList<>();
        barriersRemove = new ArrayList<>();
        points = new ArrayList<>();
        pointsRemove = new ArrayList<>();
        hearts = new ArrayList<>();
        heartsRemove = new ArrayList<>();
    }

    public void update() {
      
        for (Object object : backgrounds){
            object.move();
        }
        pointTimer++;
        barrierTimer++;
        hearthTimer++;

        if(barrierTimer >= barrierInterval) {
            switch (random.nextInt(1,4)){
                case 1 -> barriers.add(new Object(900, 195, 190, 50, "car4.png"));
                case 2 -> barriers.add(new Object(900, 275, 190, 50, "car2.png"));
                case 3 -> barriers.add(new Object(900, 365, 190, 50, "car3.png"));
            }
            barrierTimer = 0;
        }
        if(pointTimer >= pointInterval) {
            switch (random.nextInt(1,4)){
                case 1 -> points.add(new Object(900, 190, 50, 50, "gas.png"));
                case 2 -> points.add(new Object(900, 270, 50, 50, "gas.png"));
                case 3 -> points.add(new Object(900, 360, 50, 50, "gas.png"));
            }
            pointTimer = 0;
        }
        if(hearthTimer >= hearthInterval) {
            switch (random.nextInt(1, 4)){
                case 1 -> hearts.add(new Object(900, 190, 50, 50, "tire.png"));
                case 2 -> hearts.add(new Object(900, 270, 50, 50, "tire.png"));
                case 3 -> hearts.add(new Object(900, 360, 50, 50, "tire.png"));
            }
            hearthTimer = 0;
        }
        for(Object object: points){
            object.move();
        }
        for(Object object: hearts){
            object.move();
        }

        for(Object bg : backgrounds){
            if(bg.x <= -800) {
                bg.x = 800;
            }
        }

        for (Object barrier: barriers) {
            barrier.move();
            if (car.getRectangle().intersects(barrier.getRectangle())){
                car.health--;
                barriersRemove.add(barrier);
            }
            for (Object point : points) {
                if (point.getRectangle().intersects(barrier.getRectangle())) {
                    pointsRemove.add(point);
                }
                if (point.x < -100) {
                    pointsRemove.add(point);
                }
                if (barrier.x < -100) {
                    barriersRemove.add(barrier);
                }
                if (car.getRectangle().intersects(point.getRectangle())) {
                    score++;
                    if (barrierInterval >= 70) {
                        barrierInterval -= 5;
                    }
                    pointsRemove.add(point);
                }
            for(Object hearth : hearts) {
                if (car.getRectangle().intersects((hearth.getRectangle())) && car.health < 3) {
                    car.health++;
                    heartsRemove.add(hearth);
                } else if (hearth.getRectangle().intersects(barrier.getRectangle())) {
                    heartsRemove.add(hearth);
                }
                if (point.getRectangle().intersects(hearth.getRectangle())) {
                    heartsRemove.add(hearth);
                    pointsRemove.add(point);
                }
            }
        }
        for(Object hearth: hearts) {
            if (hearth.x < -100) {
                heartsRemove.add(hearth);
            }
        }
    }
        points.removeAll(pointsRemove);
        barriers.removeAll(barriersRemove);
        hearts.removeAll(heartsRemove);
        if(car.health <= 0){
            gameActive = 2;
        }

    }
    public void reset(){
        barriers.clear();
        points.clear();
        hearts.clear();
        score = 0;
        pointTimer = 0;
        barrierTimer = 0;
        hearthTimer = 0;
        barrierInterval = 150;
        car.health = 3;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE && gameActive == 0){
            gameActive = 1;
            reset();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE && gameActive == 2){
            gameActive = 1;
            reset();
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE && gameActive == 0){
            System.exit(1);
        }

        if(e.getKeyCode() == KeyEvent.VK_R && !gameRules && gameActive == 0){
            gameRules = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_R && gameRules && gameActive == 0){
            gameRules = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_M && gameActive == 2){
            gameActive = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
