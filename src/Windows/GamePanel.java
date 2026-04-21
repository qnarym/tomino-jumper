package Windows;

import Player.*;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private int x;
    private int y;
    private Player player;


    public Player getPlayer() {
        return player;
    }

    public GamePanel(int x, int y) {
        this.y = y;
        this.x = x;
        player = new Player(x,y);

        addKeyListener(new Movement(player));
        setFocusable(true);
        setSize(JFrame.MAXIMIZED_HORIZ,JFrame.MAXIMIZED_VERT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(player.getX(), player.getY(), 50, 50);
    }

}
