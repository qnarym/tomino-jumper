package Windows;

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
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         setAlwaysOnTop(true);

         JPanel panel = new JPanel();


            JLabel fpsLabel = new JLabel("FPS:");
            JSlider fpsSettings = new JSlider(0,120,90);
            fpsSettings.setSnapToTicks(true);
            fpsSettings.setMajorTickSpacing(30);
            fpsSettings.setMinorTickSpacing(5);
            fpsSettings.setPaintTicks(true);
            fpsSettings.setPaintLabels(true);

            fpsSettings.getModel().addChangeListener(e -> {
                titleScreen.setFps(fpsSettings.getValue());
            });


            JComboBox resolutionBox = new JComboBox(resolution);
            resolutionBox.setSelectedIndex(0);
            resolutionBox.setVisible(true);

            resolutionBox.addActionListener(e -> {
                titleScreen.setSelectedResolution(resolutionBox.getSelectedIndex());
            });

         panel.add(resolutionBox,BorderLayout.SOUTH);
         panel.add(fpsLabel);
         panel.add(fpsSettings);


            add(panel, BorderLayout.CENTER);
         setSize(600, 500);
         setVisible(true);
     }

}
