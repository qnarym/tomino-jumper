package Windows;

import Player.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

    private int x;
    private int y;
    private Player player;
    private ImageIcon image;
    private BufferedImage character;

    private Dimension dimension;
    private double resolutionMultiplier;

    private boolean levelChanged;

    public GamePanel(int startX, int startY, Dimension dimension) {
        this.dimension = dimension;
        resolutionMultiplier = dimension.getWidth()/1600;

        this.y = (int)(startY*resolutionMultiplier);
        this.x = (int)(startX*resolutionMultiplier);
        player = new Player(x,y, dimension);
        player.setCurrLevel(0);

        try{
            image =  new ImageIcon(this.getClass().getClassLoader().getResource(player.getLevel().getLevel(player.getCurrLevel())));
            image.setImage(image.getImage().getScaledInstance(dimension.width,dimension.height,Image.SCALE_SMOOTH));
        }catch (NullPointerException e){
            System.out.println("missing file");
        }

        try {
            InputStream characterF = this.getClass().getClassLoader().getResourceAsStream("character/standingR.png");
            if(characterF == null){
                throw new RuntimeException("Level map not found");
            }
            character = ImageIO.read(characterF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addKeyListener(new Movement(player));
        setFocusable(true);
        setSize(dimension);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        if(levelChanged){
            try{
                image =  new ImageIcon(this.getClass().getClassLoader().getResource(player.getLevel().getLevel(player.getCurrLevel())));
                image.setImage(image.getImage().getScaledInstance(dimension.width,dimension.height,Image.SCALE_SMOOTH));
            }catch (NullPointerException e){
                System.out.println("missing file");
            }
            levelChanged = false;
        }

        try {
            InputStream characterF = this.getClass().getClassLoader().getResourceAsStream(player.getPlayerAnimation(player.getStatus()));
            if(characterF == null){
                throw new RuntimeException("Level map not found");
            }
            character = ImageIO.read(characterF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        g.drawImage(image.getImage(),0,0,null);
        g.setColor(Color.pink);
        g.drawImage(character, player.getX(), player.getY(),  (int)(40*resolutionMultiplier),(int)(50*resolutionMultiplier)  , null);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public double getResolutionMultiplier() {
        return resolutionMultiplier;
    }

    public boolean isLevelChanged() {
        return levelChanged;
    }

    public void setLevelChanged(boolean levelChanged) {
        this.levelChanged = levelChanged;
    }

    public Player getPlayer() {
        return player;
    }

}
