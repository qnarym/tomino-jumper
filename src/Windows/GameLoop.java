package Windows;

import Audio.AudioPlayer;
import Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(player.getLevel().getLevel(player.getCurrLevel()));
            if(input == null){
                throw new RuntimeException("Level map not found");
            }
            image = ImageIO.read(input);
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
                if (player.getFallingSpeed()==0 && !player.getDir().equals("Jright") && player.getDir().equals("right")){
                    player.setStatus(0);
                }
                if (player.getFallingSpeed()==0 && !player.getDir().equals("Jleft") && player.getDir().equals("left")){
                    player.setStatus(1);
                }
            } else if (!player.isJumping() && player.getJumpForce()==0 && player.getJumpForceR()==0 && player.getJumpForceL()==0 && !player.getLevel().checkPlatformCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]) {
                player.setFalling(true);
                if (player.getFallingSpeed()<(int)(6* panel.getResolutionMultiplier())){
                    player.setFallingSpeed(player.getFallingSpeed()+1);
                }
            }

            /**
             * Level background changing logic
             */
            if(player.getY()<0){
                player.setCurrLevel(player.getCurrLevel()+1);
                player.setCoins(player.getCoins()+10);
                System.out.println("level changed: " +player.getCurrLevel());
                player.setY((int)(1000*panel.getResolutionMultiplier()));
                panel.setLevelChanged(true);
                panel.repaint();
            }
            else if(player.getY()>(int)(1010*panel.getResolutionMultiplier())&&player.getCurrLevel()!=0){
                player.setCurrLevel(player.getCurrLevel()-1);
                player.setCoins(player.getCoins()-10);
                System.out.println("level changed: " +player.getCurrLevel());
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
                    player.setY(player.getY() - (int)(15*panel.getResolutionMultiplier()) );
                }
            }
            else if (!player.isJumping()&&player.getJumpForceL()>0) {
                player.setJumpForceL(player.getJumpForceL() - 5);
                if(!player.getLevel().checkHeadCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())) {
                    player.setY(player.getY() - (int)(10*panel.getResolutionMultiplier()) );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                    player.setX(player.getX() - (int)(5*panel.getResolutionMultiplier()));
                    player.setVelocity(-40);
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
                    player.setY(player.getY() - (int)(10* panel.getResolutionMultiplier()) );
                }
                if (!player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]){
                    player.setX(player.getX() + (int)(5*panel.getResolutionMultiplier()));
                    player.setVelocity(40);
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
                    player.setVelocity((int)(30*panel.getResolutionMultiplier()));
                }
            }
            else if (player.isWalkingL()&&!panel.getPlayer().isJumping() && !player.isFalling()) {
                if (player.getVelocity()!=-5){
                    player.setVelocity((int)(-30*panel.getResolutionMultiplier()));
                }
            }

            if (player.getVelocity()!=0 && !panel.getPlayer().isJumping()){
                if (player.getVelocity()<0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0] && !player.isFalling()){
                    player.setX(player.getX() + (int)player.getVelocity()/15);
                    player.setStatus(3);
                    player.setDir("left");
                }
                else if (player.getVelocity()>0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1] && !player.isFalling()) {
                    player.setX(player.getX() + (int)player.getVelocity()/15);
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
                    System.out.print("left jumping: " + player.getJumpForceL() +"%\r");
                    player.setStatus(5);
                    player.setDir("Jleft");
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
                    System.out.print("right jumping:  " + player.getJumpForceR() +"%\r");
                    player.setStatus(4);
                    player.setDir("Jright");
                }
            }
//            else if (player.isJumping() && player.getJumpForceR()==0 && player.getJumpForceL()==0 && !player.isFalling()  ) {
//                try {
//                    Thread.sleep(32);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                if(player.isJumping()&&player.getJumpForce()<150 ){
//                    player.setJumpForce(player.getJumpForce()+10);
//                }
//            }

            /**
             * player sliding movement when on slope
             */
            if (player.getLevel().checkSlopedCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                int sliding = (int)player.getFallingSpeed();
                player.setX(player.getX() - sliding*2);
                System.out.println("sliding left");
            }
            if (player.getLevel().checkSlopedCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]){
                int sliding = (int)player.getFallingSpeed();
                player.setX(player.getX() + sliding*2);
                System.out.println("sliding right");
            }


            /**
             * player movement when falling
             */
            if (player.isFalling()){
                player.setY(player.getY()+ (int)player.getFallingSpeed());

                switch (player.getDir()){
                    case "left" -> player.setStatus(7);
                    case "right" -> player.setStatus(6);
                }

                if (player.getVelocity()<0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[0]){
                    player.setX(player.getX() + (int)player.getVelocity()/5);
                    player.setStatus(7);
                }
                else if (player.getVelocity()>0 && !player.getLevel().checkWallCollision(panel.isLevelChanged(), player.getCurrLevel(), player.getX(), player.getY())[1]) {

                    player.setX(player.getX() + (int)player.getVelocity()/5);
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
