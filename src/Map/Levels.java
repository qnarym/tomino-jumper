package Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Levels {

    private int currLevel;
    private final String[] levelMap = {"levels/level11.png","levels/level12.png","levels/level21.png","levels/level22.png","levels/level31.png"};
    private final String[] collisionMap = {"levels/collisionMaps/clevel11.png","levels/collisionMaps/clevel12.png","levels/collisionMaps/clevel21.png","levels/collisionMaps/clevel22.png","levels/collisionMaps/clevel31.png"};

    private final int[] collisionColors = {new Color(255,0,0).getRGB(), new Color(69,50,40).getRGB()};
    private final int[] slopedCollisionColors = {new Color(0,255,255).getRGB(), new Color(255,0,255).getRGB()};
    private ImageIcon imageIcon;
    private BufferedImage bufferedImage;

    private Dimension dimension;
    private double resolutionMultiplier;

    public Levels(int currLevel, Dimension dimension, double resolutionMultiplier) {
        this.currLevel = currLevel;
        this.dimension = dimension;
        this.resolutionMultiplier = resolutionMultiplier;

        try{
            imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(collisionMap[currLevel]));
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.dimension.width,this.dimension.height,Image.SCALE_SMOOTH));

            bufferedImage = new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
            bufferedImage.createGraphics().drawImage(imageIcon.getImage(),0,0,dimension.width,dimension.height,null);
        }catch (NullPointerException e){
            System.out.println("missing file");
        }

    }

    public boolean[] checkSlopedCollision(boolean levelChanged, int playerLevel, int x, int y){
        boolean collisionL = false;
        boolean collisionR = false;
        boolean collisionC = false;

        boolean slidingR = false;
        boolean slidingL = false;
        int foundColor = 0;

        y = (int)(y+(50*resolutionMultiplier)+2);

        if (levelChanged) {
            try{
                imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(collisionMap[playerLevel]));
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.dimension.width,this.dimension.height,Image.SCALE_SMOOTH));

                bufferedImage = new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
                bufferedImage.createGraphics().drawImage(imageIcon.getImage(),0,0,dimension.width,dimension.height,null);
            }catch (NullPointerException e){
                System.out.println("missing file");
            }
        }
        for (int i = 0; i < slopedCollisionColors.length; i++){
            int xC = (int)(x+20*resolutionMultiplier);
            try {
                if (bufferedImage.getRGB(xC, y) == slopedCollisionColors[i]){
                    collisionC = true;
                    foundColor = slopedCollisionColors[i];
                    System.out.println("collision foundC");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }
        }
        for (int i = 0; i <slopedCollisionColors.length; i++){
            int xL = x+2;
            try {
                if (bufferedImage.getRGB(xL, y) == slopedCollisionColors[i]){
                    collisionL = true;
                    foundColor = slopedCollisionColors[i];
                    System.out.println("collision foundL");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }
        for (int i = 0; i <slopedCollisionColors.length; i++){
            int xR = (int)(x+38*resolutionMultiplier);
            try {
                if (bufferedImage.getRGB(xR, y) == slopedCollisionColors[i]){
                    collisionR = true;
                    foundColor = slopedCollisionColors[i];
                    System.out.println("collision foundR");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }

        if (collisionL || collisionR || collisionC){
            switch (foundColor){
                case -16711681 -> slidingL = true;
                case -65281 -> slidingR = true;
            }
        }

        return new boolean[]{slidingL,slidingR};

    }

    public boolean[] checkPlatformCollision(boolean levelChanged, int playerLevel, int x, int y) {
        boolean collisionL = false;
        boolean collisionR = false;
        boolean collisionC = false;

        y = (int)(y+(50*resolutionMultiplier)+2);

        if (levelChanged) {
            try{
                imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(collisionMap[playerLevel]));
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.dimension.width,this.dimension.height,Image.SCALE_SMOOTH));

                bufferedImage = new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
                bufferedImage.createGraphics().drawImage(imageIcon.getImage(),0,0,dimension.width,dimension.height,null);
            }catch (NullPointerException e){
                System.out.println("missing file");
            }
        }


        for (int i = 0; i <collisionColors.length; i++){
            x = (int)(x+20*resolutionMultiplier);
            try {
                if (bufferedImage.getRGB(x, y) == collisionColors[i]){
                    collisionC = true;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }
        for (int i = 0; i <collisionColors.length; i++){
            x = x+5;
            try {
                if (bufferedImage.getRGB(x, y) == collisionColors[i]){
                    collisionL = true;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }
        for (int i = 0; i <collisionColors.length; i++){
            x = (int)(x+20*resolutionMultiplier+5);
            try {
                if (bufferedImage.getRGB(x, y) == collisionColors[i]){
                    collisionR = true;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }
        return new boolean[]{collisionL,collisionC,collisionR};
    }

    public boolean checkHeadCollision(boolean levelChanged, int playerLevel, int x, int y) {
        boolean collision = false;
        x = x+15;
        y = y-5;

        if (levelChanged) {
            try{
                imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(collisionMap[playerLevel]));
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.dimension.width,this.dimension.height,Image.SCALE_SMOOTH));

                bufferedImage = new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
                bufferedImage.createGraphics().drawImage(imageIcon.getImage(),0,0,dimension.width,dimension.height,null);
            }catch (NullPointerException e){
                System.out.println("missing file");
            }
        }

        for (int i = 0; i <collisionColors.length; i++){
            try {
                if (bufferedImage.getRGB(x, y) == collisionColors[i] && y>20){
                    collision = true;
//                    System.out.println("head collision");
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | "+e.getMessage());
            }

        }
        return collision;
    }

    public boolean[] checkWallCollision(boolean levelChanged, int playerLevel, int x, int y) {
        int x1 = x-1;
        int x2 = x+(int)(40*resolutionMultiplier+1);
        y = y+10;

        boolean collisionR = false;
        boolean collisionL = false;

        if (levelChanged) {
            try{
                imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(collisionMap[playerLevel]));
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.dimension.width,this.dimension.height,Image.SCALE_SMOOTH));

                bufferedImage = new BufferedImage(dimension.width,dimension.height,BufferedImage.TYPE_INT_ARGB);
                bufferedImage.createGraphics().drawImage(imageIcon.getImage(),0,0,dimension.width,dimension.height,null);
            }catch (NullPointerException e){
                System.out.println("missing file");
            }
        }
        for (int i = 0; i <collisionColors.length; i++){
            try{
                for (int j = 0; j < (int)(30*resolutionMultiplier) ; j++) {
                    if (bufferedImage.getRGB(x1, y+j) == collisionColors[i]){
                        collisionL = true;
//                        System.out.println("wall collision found left");
                    }
                    else if (bufferedImage.getRGB(x2, y+j) == collisionColors[i]){
                        collisionR = true;
//                        System.out.println("wall collision found right");
                    }
                }

            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("player is out of bounds (might be due to level change) | " + e.getMessage());
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
