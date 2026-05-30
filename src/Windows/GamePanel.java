package Windows;

import Player.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Panel with whole game that is added into GameScreen, takes care of chargebar values and whole graphics of the game
 */
public class GamePanel extends JPanel {

    private int x;
    private int y;
    private Player player;
    private ImageIcon image;
    private BufferedImage character;
    private JProgressBar charge;

    private boolean gameComplete;
    private GameScreen gameScreen;

    private Dimension dimension;
    private double resolutionMultiplier;

    private boolean levelChanged;

    public GamePanel(int startX, int startY, Dimension dimension, JProgressBar chargeBar, GameScreen gameScreen) {
        this.dimension = dimension;
        resolutionMultiplier = dimension.getWidth()/1600;

        this.charge = chargeBar;
        this.gameScreen = gameScreen;

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

    public void gameComplete(){
        gameScreen.gameComplete();
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

        if(player.getJumpForceR()==0&&player.getJumpForceL()==0||!player.isJumping()){
            charge.setVisible(false);
        }
        else if(player.getJumpForceR()>=0&&player.getJumpForceL()==0){
            charge.setValue((int) player.getJumpForceR());
            charge.setBounds(player.getX()-30, (int)(player.getY()+60*resolutionMultiplier), 100,20);
            charge.setVisible(true);
            switch ((int)player.getJumpForceR()){
                case 0 -> charge.setForeground(new Color(101, 10, 10));
                case 30 -> charge.setForeground(new Color(136, 96, 0));
                case 60 -> charge.setForeground(new Color(165, 131, 0));
                case 90 -> charge.setForeground(new Color(153, 161, 0));
                case 120 -> charge.setForeground(new Color(110, 166, 0));
                case 150 -> charge.setForeground(new Color(0, 175, 0));
            }
        }
        else if(player.getJumpForceL()>=0&&player.getJumpForceR()==0){
            charge.setValue((int) player.getJumpForceL());
            charge.setBounds(player.getX()-30, (int)(player.getY()+60*resolutionMultiplier), 100,20);
            charge.setVisible(true);
            switch ((int)player.getJumpForceL()){
                case 0 -> charge.setForeground(new Color(101, 10, 10));
                case 30 -> charge.setForeground(new Color(136, 96, 0));
                case 60 -> charge.setForeground(new Color(165, 131, 0));
                case 90 -> charge.setForeground(new Color(153, 161, 0));
                case 120 -> charge.setForeground(new Color(110, 166, 0));
                case 150 -> charge.setForeground(new Color(0, 175, 0));
            }
        }
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
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
