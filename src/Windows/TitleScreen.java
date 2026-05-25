package Windows;

import javax.swing.*;
import java.awt.*;

public class TitleScreen extends JFrame {



    public TitleScreen() {
          super("tomino jumper: Main menu");
    }

    private int fps = 90;
    private int selectedResolution = 1;

    private String[] backgroundVar = {"titledzamp.png","goy.png","swag.png","tsBackground.png"};
    private int backgroundIndex = 0;


    public void init() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel buttonPanel = new JPanel();


            JButton startGame = new JButton("Start Game");
            JCustoms.startButton(startGame);
            startGame.setFocusable(false);
            JButton settingsButton = new JButton("Settings");
            JCustoms.settingsButton(settingsButton);
            settingsButton.setFocusable(false);
            JButton exitButton = new JButton("Exit");
            JCustoms.exitButton(exitButton);
            exitButton.setFocusable(false);

            buttonPanel.add(startGame);
            buttonPanel.add(settingsButton);
            buttonPanel.add(exitButton);
            buttonPanel.setOpaque(false);

            ImageIcon titleBackground = new ImageIcon(this.getClass().getClassLoader().getResource(backgroundVar[backgroundIndex]));
            titleBackground.setImage(titleBackground.getImage().getScaledInstance(850,600, Image.SCALE_SMOOTH));
            ImageIcon title = new ImageIcon(this.getClass().getClassLoader().getResource("tominojumper.gif"));
            JLabel titleText = new JLabel(title);
            titleText.setBackground(Color.white);


            JButton backgroundButton = new JButton();
            JCustoms.setBackground(backgroundButton);
            backgroundButton.setFocusable(false);

            JPanel coolPanel = new JPanel();
            coolPanel.add(backgroundButton);
            coolPanel.setOpaque(false);

            JLabel titleLabel = new JLabel(titleBackground);
            titleLabel.setLayout(new BorderLayout());
            titleLabel.add(titleText,BorderLayout.NORTH);
            titleLabel.add(buttonPanel, BorderLayout.SOUTH);
            titleLabel.add(coolPanel, BorderLayout.EAST);
            add(titleLabel, BorderLayout.CENTER);

        backgroundButton.addActionListener(e -> {
            backgroundIndex++;
            if (backgroundIndex >= backgroundVar.length) {
                backgroundIndex = 0;
            }
            titleBackground.setImage(new ImageIcon(this.getClass().getClassLoader().getResource(backgroundVar[backgroundIndex])).getImage().getScaledInstance(850,600, Image.SCALE_SMOOTH));
            repaint();
            backgroundButton.repaint();
        });

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
