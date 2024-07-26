package org.example;

public class Object extends Entity {
    int speed = 8;


    public Object(int x, int y, int width, int height, String file) {
        super(x,y, width, height);
        setImage(file);
    }

public void move() {
        x -= speed;
    }
}