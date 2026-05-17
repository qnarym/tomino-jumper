package Windows;

import Player.*;

import javax.swing.*;
import java.awt.*;


public class GameScreen extends JFrame{

    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private Thread gameThread;
    private final int[] possibleWidth = {1600,1200,800};
    private final int[] possibleHeight = {1080,820,600};
    private Dimension dimension;


    public GameScreen(int fps, int selectedResolution) {
            super("tomino jumper: jumping");

            dimension = new Dimension(possibleWidth[selectedResolution],possibleHeight[selectedResolution]);

            gamePanel = new GamePanel(700,900, dimension);
            gameLoop = new GameLoop(gamePanel, gamePanel.getPlayer(), fps);
            gameThread = new Thread(gameLoop);
        }

        public void init () {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(dimension);
            gameThread.start();

            add(gamePanel);
            setLocationRelativeTo(null);
            setVisible(true);

        }
}





