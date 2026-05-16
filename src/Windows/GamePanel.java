package Windows;

import Player.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GamePanel extends JPanel {

    private int x;
    private int y;
    private Player player;
    private BufferedImage image;
    private BufferedImage character;

    private boolean levelChanged;

    public boolean isLevelChanged() {
        return levelChanged;
    }

    public void setLevelChanged(boolean levelChanged) {
        this.levelChanged = levelChanged;
    }

    public Player getPlayer() {
        return player;
    }

    public GamePanel(int startX, int startY) {
        this.y = startY;
        this.x = startX;
        player = new Player(x,y);
        player.setCurrLevel(0);

        try {
            FileInputStream fis = new FileInputStream(player.getLevel().getLevel(player.getCurrLevel()));
            image = ImageIO.read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream characterF = new FileInputStream("res/character/standingR.png");
            character = ImageIO.read(characterF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addKeyListener(new Movement(player));
        setFocusable(true);
        setSize(1400,1080);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (levelChanged) {
            try {
                FileInputStream fis = new FileInputStream(player.getLevel().getLevel(player.getCurrLevel()));
                image = ImageIO.read(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            levelChanged = false;
        }


        try {
            FileInputStream characterF = new FileInputStream(player.getPlayerAnimation(player.getStatus()));
            character = ImageIO.read(characterF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g.drawImage(image,0,0,null);
        g.setColor(Color.pink);
        g.drawImage(character, player.getX(), player.getY(), 40,50  , null);
    }

}
