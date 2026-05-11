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

            if(!player.isJumping()&&player.getJumpForce()>0&&player.getJumpForceR()>0) {
                player.setJumpForceL(0);
                player.setJumpForce(player.getJumpForce() - 5);
                player.setJumpForceR(player.getJumpForceR() - 5);

                player.setY(player.getY() - 7);
                player.setX(player.getX() + 7);
                System.out.println("Y"+player.getY());
                System.out.println("RF"+player.getJumpForceR());
            }
            if(!player.isJumping()&&player.getJumpForce()>0&&player.getJumpForceL()>0) {
                player.setJumpForceR(0);
                player.setJumpForce(player.getJumpForce() - 5);
                player.setJumpForceL(player.getJumpForceL() - 5);

                player.setY(player.getY() - 7);
                player.setX(player.getX() - 7);
                System.out.println("Y"+player.getY());
                System.out.println("LF"+player.getJumpForceL());
            }
            if(!player.isJumping()&&player.getJumpForce()>0) {
                player.setJumpForce(player.getJumpForce() - 5);
                player.setY(player.getY() - 10);
                System.out.println("Y"+player.getY());
            }

            else if (panel.getPlayer().isWalkingR()&&!panel.getPlayer().isJumping()) {
                System.out.println("right");
                player.setX(player.getX()+5);
            }else if (player.isWalkingL()&&!panel.getPlayer().isJumping()) {
                player.setX(player.getX()-5);
                System.out.println("left");


            }

            else if (panel.getPlayer().isJumping()&&panel.getPlayer().isWalkingL()&&!panel.getPlayer().isWalkingR()) {
                try {
                    Thread.sleep(128);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(player.isJumping()&&player.getJumpForce()<100&&player.getJumpForceL()<100) {



                    player.setJumpForce(player.getJumpForce()+10);
                    player.setJumpForceL(player.getJumpForceL()+10);
                    System.out.println("LF"+player.getJumpForceL());

                }
            }else if (panel.getPlayer().isJumping()&&panel.getPlayer().isWalkingR()&&!panel.getPlayer().isWalkingL()) {
                try {
                    Thread.sleep(128);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(player.isJumping()&&player.getJumpForce()<100&&player.getJumpForceR()<100){



                    player.setJumpForce(player.getJumpForce()+10);
                    player.setJumpForceR(player.getJumpForceR()+10);
                    System.out.println("RF"+player.getJumpForceR());

                }
            }


            else if (player.isJumping()) {

                try {
                    Thread.sleep(128);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(player.isJumping()&&player.getJumpForce()<100){



                    player.setJumpForce(player.getJumpForce()+10);
                    System.out.println("JF"+player.getJumpForce());

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
