package Map;

import Player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


public class Levels {

    private int currLevel;
    private final String[] levelMap = {"res/levels/level11.png","res/levels/level12.png"};
    private final String[] collisionMap = {"res/levels/collisionMaps/clevel11.png","res/levels/collisionMaps/clevel12.png"};

    private final int[] collisionColors = {new Color(255,0,0).getRGB(), new Color(69,50,40).getRGB()};
    private BufferedImage image;


    private FileInputStream fis;

    public Levels(int currLevel) {
        this.currLevel = currLevel;

        try {
            fis = new FileInputStream(collisionMap[currLevel]);
            image = ImageIO.read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean checkPlatformCollision(boolean levelChanged, int playerLevel, int x, int y) {
        boolean collision = false;
        x = x+15;
        y = y+52;

        if (levelChanged) {
            try {
                fis = new FileInputStream(collisionMap[playerLevel]);
                image = ImageIO.read(fis);
                System.out.println(" collision map changed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        for (int i = 0; i <collisionColors.length; i++){
            try {
                if (image.getRGB(x, y) == collisionColors[i]){
                    collision = true;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds "+e.getMessage());
            }

        }
        return collision;
    }

    public boolean checkHeadCollision(boolean levelChanged, int playerLevel, int x, int y) {
        boolean collision = false;
        x = x+15;

        if (levelChanged) {
            try {
                fis = new FileInputStream(collisionMap[playerLevel]);
                image = ImageIO.read(fis);
                System.out.println(" collision map changed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i <collisionColors.length; i++){
            try {
                if (image.getRGB(x, y) == collisionColors[i] && y>20){
                    collision = true;
                    System.out.println("head collision");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds "+e.getMessage());
            }

        }
        return collision;
    }

    public boolean[] checkWallCollision(boolean levelChanged, int playerLevel, int x, int y) {
        int x1 = x-1;
        int x2 = x+31;
        y = y+25;

        boolean collisionR = false;
        boolean collisionL = false;

        if (levelChanged) {
            try {
                fis = new FileInputStream(collisionMap[playerLevel]);
                image = ImageIO.read(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i <collisionColors.length; i++){
            try{
                if (image.getRGB(x1, y) == collisionColors[i]){
                    collisionL = true;
                    System.out.println("wall collision found left");

                }
                else if (image.getRGB(x2, y) == collisionColors[i]){
                    collisionR = true;
                    System.out.println("wall collision found right");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds " + e.getMessage());
            }

        }
        return new boolean[]{collisionL, collisionR};
    }

    public String getLevel(int currLevel){
        return levelMap[currLevel];
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

}
