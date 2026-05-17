package Windows;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TitleScreen extends JFrame {



    public TitleScreen() {
          super("tomino jumper: Main menu");
    }

    private int fps = 90;
    private int selectedResolution = 0;


    public void init() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();


            JButton startGame = new JButton("Start Game");
            panel.add(startGame);
            startGame.setFocusable(false);
            JButton settingsButton = new JButton("Settings");
            panel.add(settingsButton);
            settingsButton.setFocusable(false);
            JButton exitButton = new JButton("Exit");
            panel.add(exitButton);
            exitButton.setFocusable(false);


        add(panel, BorderLayout.SOUTH);

            ImageIcon title = new ImageIcon(this.getClass().getClassLoader().getResource("tominojumper.gif"));
            add(new JLabel(title), BorderLayout.CENTER);

        startGame.addActionListener(e -> {
            dispose();
            System.out.println(fps);
            System.out.println(selectedResolution);
            new GameScreen(fps, selectedResolution).init();
        });

        settingsButton.addActionListener(e -> {
            new SettingsScreen(this, this).init();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });



        setVisible(true);
    }


    public void setSelectedResolution(int selectedResolution) {
        this.selectedResolution = selectedResolution;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }
}
