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
    private int FPS = 90;
    private BufferedImage image;


    public GameLoop(GamePanel panel, Player player, int FPS) {
        this.panel = panel;
        this.player = player;
        this.FPS = FPS;

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
            if (player.getLevel().checkPlatformCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]) {
                player.setFalling(false);
                player.setFallingSpeed(0);
            } else if (!player.isJumping() && player.getJumpForce()==0 && player.getJumpForceR()==0 && player.getJumpForceL()==0 && !player.getLevel().checkPlatformCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]) {
                player.setFalling(true);
                if (player.getFallingSpeed()<6){
                    player.setFallingSpeed(player.getFallingSpeed()+1);
                }
            }

            /**
             * Level background changing logic
             */
            if(player.getY()<0){
                player.setCurrLevel(player.getCurrLevel()+1);
                System.out.println("level changed");
                player.setY(1000);
                panel.setLevelChanged(true);
                panel.repaint();
            }
            else if(player.getY()>1010&&player.getCurrLevel()!=0){
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
            }
            else if (!player.isJumping()&&player.getJumpForceL()>0) {
                player.setJumpForceL(player.getJumpForceL() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - 10 );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                    player.setX(player.getX() - 5);
                    player.setVelocity(-45);
                    player.setDir("Jleft");
                }
                else{
                    player.setJumpForceR(40);
                    player.setJumpForceL(0);
                    AudioPlayer.playSound("/sounds/slap.wav");
                }
            }
            else if (!player.isJumping()&&player.getJumpForceR()>0) {
                player.setJumpForceR(player.getJumpForceR() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - 10 );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]){
                    player.setX(player.getX() + 5);
                    player.setVelocity(45);
                    player.setDir("Jright");
                }
                else{
                    player.setJumpForceL(40);
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
                    player.setStatus(3);
                    player.setDir("left");
                }
                else if (player.getVelocity()>0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1] && !player.isFalling()) {
                    player.setX(player.getX() + player.getVelocity()/15);
                    player.setStatus(2);
                    player.setDir("right");
                }

                if (!player.isWalkingR() && player.getVelocity()>0 && !player.isJumping()){
                    player.setVelocity(player.getVelocity() -1);
                    if (player.getVelocity()==0){
                        player.setStatus(0);
                    }
                }
                else if (!player.isWalkingL() && player.getVelocity()<0 && player.getJumpForce()==0 && player.getJumpForceL()==0 && player.getJumpForceR()==0){
                    player.setVelocity(player.getVelocity() +1);
                    if (player.getVelocity()==0){
                        player.setStatus(1);
                    }
                }


            }

            /**
             * logic of charging the jump (diagonal+straight up)
             */
             if (player.isJumping() && player.isWalkingL() && player.getJumpForceR()==0 && player.getJumpForce()==0 && !player.isFalling()  ) {
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceL()<150){
                    player.setJumpForceL(player.getJumpForceL()+10);
                    System.out.println("left jumping");
                    player.setStatus(5);
                }
            }
             if (player.isJumping()&&player.isWalkingR() && player.getJumpForceL()==0 && player.getJumpForce()==0 && !player.isFalling()){
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForceR()<150){
                    player.setJumpForceR(player.getJumpForceR()+10);
                    System.out.println("right jumping");
                    player.setStatus(4);
                }
            }
            else if (player.isJumping() && player.getJumpForceR()==0 && player.getJumpForceL()==0 && !player.isFalling()  ) {
                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(player.isJumping()&&player.getJumpForce()<150 ){
                    player.setJumpForce(player.getJumpForce()+10);
                }
            }

            /**
             * player movement when falling
             */
            if (player.isFalling()){
                player.setY(player.getY()+ player.getFallingSpeed());

                switch (player.getDir()){
                    case "left" -> player.setStatus(7);
                    case "right" -> player.setStatus(6);
                }

                if (player.getVelocity()<0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                    player.setX(player.getX() + player.getVelocity()/5);
                    player.setStatus(7);
                }
                else if (player.getVelocity()>0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]) {
                    player.setX(player.getX() + player.getVelocity()/5);
                    player.setStatus(6);
                }

                if (player.getVelocity()>0){
                    player.setVelocity(player.getVelocity()-1);
                    player.setDir("right");
                }
                else if (player.getVelocity()<0) {
                    player.setVelocity(player.getVelocity()+1);
                    player.setDir("left");
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
