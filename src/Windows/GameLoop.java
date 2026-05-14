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

            //movement logic
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!player.isJumping()&&player.getJumpForce()>0) {
                player.setJumpForce(player.getJumpForce() - 10);
                player.setY(player.getY() - 20);
                System.out.println("Y"+player.getY());
            }
            else if (!player.isJumping()&&player.getJumpForceL()>0) {
                player.setJumpForceL(player.getJumpForceL() - 10);
                player.setY(player.getY() - 20);
                player.setX(player.getX() - 10);
            }
            else if (!player.isJumping()&&player.getJumpForceR()>0) {
                player.setJumpForceR(player.getJumpForceR() - 10);
                player.setY(player.getY() - 20);
                player.setX(player.getX() + 10);
            }

            else if (panel.getPlayer().isWalkingR()&&!panel.getPlayer().isJumping()) {
                System.out.println("right");
                player.setX(player.getX()+5);
            }
            else if (player.isWalkingL()&&!panel.getPlayer().isJumping()) {
                player.setX(player.getX()-5);
                System.out.println("left");
            }


            else if (player.isJumping() && player.isWalkingL()) {
                try {
                    Thread.sleep(64);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceL()<100){
                    player.setJumpForceL(player.getJumpForceL()+10);
                    System.out.println("left jumping");
                }
            }
            else if (player.isJumping()&&player.isWalkingR()){
                try {
                    Thread.sleep(64);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceR()<100){
                    player.setJumpForceR(player.getJumpForceR()+10);
                    System.out.println("right jumping");
                }
            }
            else if (player.isJumping()) {
                try {
                    Thread.sleep(64);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForce()<100){
                    player.setJumpForce(player.getJumpForce()+10);
                }
            }


            if (System.nanoTime() - lastTime >= timePerTick){
                panel.repaint();
                lastTime = System.nanoTime();
                frames++;
            }



            if (System.currentTimeMillis() - timer >= 1000){
                timer = System.currentTimeMillis();
                /*System.out.println("FPS: " + frames);*/
                frames = 0;
            }

        }

    }
}
