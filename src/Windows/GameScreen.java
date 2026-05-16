package Windows;

import Player.*;

import javax.swing.*;


public class GameScreen extends JFrame{

    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private Thread gameThread;


    public GameScreen(int fps) {
            super("tomino jumper: jumping");

            gamePanel = new GamePanel(700,900);
            gameLoop = new GameLoop(gamePanel, gamePanel.getPlayer(), fps);
            gameThread = new Thread(gameLoop);
        }

        public void init () {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(1600,1080);
            gameThread.start();

            add(gamePanel);
            setLocationRelativeTo(null);
            setVisible(true);

        }
}





