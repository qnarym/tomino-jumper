package Windows;

import Audio.AudioPlayer;
import Audio.MusicPlayer;
import Audio.Sound;
import Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GameLoop implements Runnable{

    private GamePanel panel;
    private final Player player;
    private final int FPS = 90;
    private BufferedImage image;


    public GameLoop(GamePanel panel, Player player){
        this.panel = panel;
        this.player = player;

        try {
            FileInputStream fis = new FileInputStream(player.getLevel().getLevel(player.getCurrLevel()));
            image = ImageIO.read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


            /**
             * logic that checks if player have collision
             */
            if (player.getLevel().checkPlatformCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                player.setFalling(false);
                player.setFallingSpeed(0);
            } else if (!player.isJumping() && player.getJumpForce()==0 && player.getJumpForceR()==0 && player.getJumpForceL()==0 &&  !player.getLevel().checkPlatformCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                player.setFalling(true);
                if (player.getFallingSpeed()<6){
                    player.setFallingSpeed(player.getFallingSpeed()+1);
                }
            }

            /**
             * Level background changing logic
             */
            if(player.getY()<5){
                player.setCurrLevel(player.getCurrLevel()+1);
                System.out.println("level changed");
                player.setY(990);
                panel.setLevelChanged(true);
                panel.repaint();
            }
            else if(player.getY()>990&&player.getCurrLevel()!=0){
                player.setCurrLevel(player.getCurrLevel()-1);
                System.out.println("level changed");
                player.setY(0);
                panel.setLevelChanged(true);
                panel.repaint();
            }

            /**
             * Player jumping logic (diagonal+straight up)
             */
            if(!player.isJumping()&&player.getJumpForce()>0) {
                player.setJumpForce(player.getJumpForce() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - 15 );
                }
                System.out.println("Y"+player.getY());
            }
            else if (!player.isJumping()&&player.getJumpForceL()>0) {
                player.setJumpForceL(player.getJumpForceL() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - 15 );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                    player.setX(player.getX() - 10);
                    player.setVelocity(-30);
                }
                else{
                    player.setJumpForceR(20);
                    player.setJumpForceL(0);
                    AudioPlayer.playSound("/sounds/slap.wav");
                }
            }
            else if (!player.isJumping()&&player.getJumpForceR()>0) {
                player.setJumpForceR(player.getJumpForceR() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - 15 );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]){
                    player.setX(player.getX() + 10);
                    player.setVelocity(30);
                }
                else{
                    player.setJumpForceL(20);
                    player.setJumpForceR(0);
                    AudioPlayer.playSound("/sounds/slap.wav");
                }
            }

            /**
             * Player basic movement
             */



            else if (panel.getPlayer().isWalkingR() && !panel.getPlayer().isJumping() && !player.isFalling()) {
                if (player.getVelocity()!=5){
                    player.setVelocity(30);
                }
            }
            else if (player.isWalkingL()&&!panel.getPlayer().isJumping() && !player.isFalling()) {
                if (player.getVelocity()!=-5){
                    player.setVelocity(-30);
                }
            }

            if (player.getVelocity()!=0 && !panel.getPlayer().isJumping()){
                if (player.getVelocity()<0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0] && !player.isFalling()){
                    player.setX(player.getX() + player.getVelocity()/15);
                }
                else if (player.getVelocity()>0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1] && !player.isFalling()) {
                    player.setX(player.getX() + player.getVelocity()/15);
                }

                if (!player.isWalkingR() && player.getVelocity()>0 && !player.isJumping()){
                    player.setVelocity(player.getVelocity() -1);
                }
                else if (!player.isWalkingL() && player.getVelocity()<0 && player.getJumpForce()==0 && player.getJumpForceL()==0 && player.getJumpForceR()==0){
                    player.setVelocity(player.getVelocity() +1);
                }


            }

            /**
             * logic of charging the jump (diagonal+straight up)
             */
            else if (player.isJumping() && player.isWalkingL() && player.getJumpForceR()==0 && player.getJumpForce()==0 && !player.isFalling()  ) {
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceL()<100){
                    player.setJumpForceL(player.getJumpForceL()+10);
                    System.out.println("left jumping");
                }
            }
            else if (player.isJumping()&&player.isWalkingR() && player.getJumpForceL()==0 && player.getJumpForce()==0 && !player.isFalling()){
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceR()<100){
                    player.setJumpForceR(player.getJumpForceR()+10);
                    System.out.println("right jumping");
                }
            }
            else if (player.isJumping() && player.getJumpForceR()==0 && player.getJumpForceL()==0 && !player.isFalling()  ) {
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForce()<100){
                    player.setJumpForce(player.getJumpForce()+10);
                }
            }

            /**
             * player movement when falling
             */
            if (player.isFalling()){
                player.setY(player.getY()+ player.getFallingSpeed());
                player.setX(player.getX() + player.getVelocity()/5);
                if (player.getVelocity()>0){
                    player.setVelocity(player.getVelocity()-1);
                }
                else if (player.getVelocity()<0){
                    player.setVelocity(player.getVelocity()+1);
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
