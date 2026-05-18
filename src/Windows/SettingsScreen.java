package Windows;

import Audio.AudioPlayer;
import Audio.Sound;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JDialog {

    private TitleScreen titleScreen;
    private final String[] resolution = {"1600x1080","1200x810","800x540"};

     public SettingsScreen(Frame owner, TitleScreen titleScreen) {
         super(owner, "tomino jumper: settings", true);
         this.titleScreen = titleScreen;

     }

     public void init() {
         setResizable(false);
         setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         setAlwaysOnTop(true);

         JPanel panel = new JPanel(new BorderLayout());


            JLabel fpsLabel = new JLabel("FPS:");
            JSlider fpsSettings = new JSlider(0,120,90);
            fpsSettings.setSnapToTicks(true);
            fpsSettings.setMajorTickSpacing(30);
            fpsSettings.setMinorTickSpacing(5);
            fpsSettings.setPaintTicks(true);
            fpsSettings.setPaintLabels(true);

            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(e -> {
                if (fpsSettings.getValue() == 0) {

//                    AudioPlayer.playSound("/sounds/surak.wav");
                    JDialog kkt = new JDialog();
                    kkt.setModal(true);
                    kkt.setAlwaysOnTop(true);
                    kkt.setLocationRelativeTo(null);
                    kkt.setSize(1280,960);

                    kkt.add(new JLabel(new ImageIcon("res/surak.gif")), BorderLayout.CENTER);
                    AudioPlayer.playSound("/sounds/surakxd.wav");




                    kkt.setVisible(true);

                }
                else {
                    dispose();
                }
            });
            panel.add(applyButton, BorderLayout.SOUTH);

            fpsSettings.getModel().addChangeListener(e -> {
                titleScreen.setFps(fpsSettings.getValue());
            });


            JComboBox resolutionBox = new JComboBox(resolution);
            resolutionBox.setSelectedIndex(0);
            resolutionBox.setVisible(true);

            resolutionBox.addActionListener(e -> {
                titleScreen.setSelectedResolution(resolutionBox.getSelectedIndex());
            });

         panel.add(resolutionBox,BorderLayout.EAST);
         panel.add(fpsLabel);
         panel.add(fpsSettings);


            add(panel, BorderLayout.CENTER);
         setSize(600, 150);
         setVisible(true);
     }

}
