package Windows;

import Player.*;

import javax.swing.*;


public class GameScreen extends JFrame{

    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private Thread gameThread;




    public GameScreen() {
            super("tomino jumper: jumping");

            gamePanel = new GamePanel(700,940);
            gameLoop = new GameLoop(gamePanel, gamePanel.getPlayer());
            gameThread = new Thread(gameLoop);
        }

        public void init () {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(1416,1093);
            gameThread.start();

            add(gamePanel);
            setLocationRelativeTo(null);
            setVisible(true);

        }


}





