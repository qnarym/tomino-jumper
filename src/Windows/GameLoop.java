package Windows;

import Player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class GameLoop implements Runnable{

    private GamePanel panel;
    private final Player player;

    private final int FPS = 120;

    public GameLoop(GamePanel panel, Player player){
        this.panel = panel;
        this.player = player;
    }

    @Override
    public void run() {

        double timePerTick = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();

        int frames = 0;
        long timer = System.currentTimeMillis();

        while(true){
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (panel.getPlayer().isWalkingR()){
                System.out.println("right");
                player.setX(player.getX()+5);
            }else if (player.isWalkingL()){
                player.setX(player.getX()-5);
                System.out.println("left");
            }else if (player.isJumping()) {

                try {
                    Thread.sleep(128);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(player.isJumping()&&player.getJumpForce()<100){



                    player.setJumpForce(player.getJumpForce()+10);
                    System.out.println(player.getJumpForce());

                }else if(!player.isJumping()&&player.getJumpForce()>0){
                    while (player.getJumpForce()!=0){
                        player.setJumpForce(player.getJumpForce()-5);
                        player.setY(player.getY()-10);
                    }
                }
            }

            if (System.nanoTime() - lastTime >= timePerTick){
                panel.repaint();
                lastTime = System.nanoTime();
                frames++;
            }

            panel.repaint();


            if (System.currentTimeMillis() - timer >= 1000){
                timer = System.currentTimeMillis();
                /*System.out.println("FPS: " + frames);*/
                frames = 0;
            }

        }

    }
}
