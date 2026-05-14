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


    public Player getPlayer() {
        return player;
    }

    public GamePanel(int x, int y) {
        this.y = y;
        this.x = x;
        player = new Player(x,y);
        try {
            FileInputStream fis = new FileInputStream("res/tsBackground.png");
            image = ImageIO.read(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        addKeyListener(new Movement(player));
        setFocusable(true);
        setSize(JFrame.MAXIMIZED_HORIZ,JFrame.MAXIMIZED_VERT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.setColor(Color.pink);
        g.fillRect(player.getX(), player.getY(), 50, 50);
    }

}
