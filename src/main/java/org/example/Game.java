package org.example;

public class Game {
    private GameLogic logic;
    private GameGraphics graphics;

    public static void main(String[] args) {
        new Game();
    }
    public Game() {
        logic = new GameLogic();
        graphics = new GameGraphics(logic);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (logic.gameActive == 1){
                        logic.update();
                    }
                    graphics.render(logic);


                    try {
                        Thread.sleep(8);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }
}
