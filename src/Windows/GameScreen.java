package Windows;

import Player.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;


public class GameScreen extends JFrame{

    private GamePanel gamePanel;
    private GameLoop gameLoop;
    private Thread gameThread;

    private final int[] possibleWidth = {2000,1600,1200};
    private final int[] possibleHeight = {1360,1080,820};

    private final String[] helpLinks = {"https://www.instagram.com/p/DRVNGzkDU_g/","https://www.instagram.com/p/DP4aAV2jYtI/","https://www.instagram.com/p/DPJu-F9DWUB/","https://www.instagram.com/p/DORYBdxjaL8/",
                                        "https://www.instagram.com/p/DOCLm6tDTPw/","https://www.instagram.com/p/DNOW8y4sDXl/","https://www.instagram.com/p/DL5PN1mKHcy/","https://www.instagram.com/p/DJcWL-5Ojev/",
                                        "https://www.instagram.com/p/DJGcwXVM5r-/","https://www.instagram.com/p/DGwCO9Luj9p/?img_index=1","https://www.instagram.com/p/DGVtus0sR1h/","https://www.instagram.com/p/DCfmseBqgsG/"};

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

            JButton helpButton = new JButton("");
            helpButton.setFocusable(false);
            helpButton.setBackground(Color.RED);
            helpButton.setPreferredSize(new Dimension(20,20));

            JPanel helpPanel = new JPanel(new BorderLayout());
            helpPanel.setSize(new Dimension(helpButton.getWidth(),helpButton.getHeight()));
            helpPanel.add(helpButton, BorderLayout.SOUTH);
            helpPanel.setBackground(Color.BLACK);

            helpButton.addActionListener(e -> {
                Random rd = new Random();
                int index = rd.nextInt(helpLinks.length);
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(helpLinks[index]));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });


            JLabel gameLabel = new JLabel();
            gameLabel.setLayout(new BorderLayout());
            gameLabel.add(gamePanel, BorderLayout.CENTER);
            gameLabel.add(helpPanel, BorderLayout.EAST);
            gameLabel.setOpaque(true);

            add(gameLabel);
            setLocationRelativeTo(null);
            setVisible(true);

        }
}





