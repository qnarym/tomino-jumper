package Windows;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TitleScreen extends JFrame {



    public TitleScreen() {
          super("tomino jumper: Main menu");
    }


    public void init() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

            JButton startGame = new JButton("Start Game");
            panel.add(startGame);
            JButton settingsButton = new JButton("Settings");
            panel.add(settingsButton);
            JButton exitButton = new JButton("Exit");
            panel.add(exitButton);

            add(panel, BorderLayout.SOUTH);

            add(new JLabel(new ImageIcon("res/tominojumper.gif")), BorderLayout.CENTER);

        startGame.addActionListener(e -> {
            dispose();
            new GameScreen().init();

        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });



        setVisible(true);
    }
}
