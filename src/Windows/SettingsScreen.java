package Windows;

import Audio.AudioPlayer;
import Audio.Sound;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Class  for settings screen, returns value that other classes use to select desired size or adjust values
 */
public class SettingsScreen extends JDialog {

    private TitleScreen titleScreen;
    private final String[] resolution = {"2000x1350","1600x1080","1200x810"};

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



         JPanel panel = new JPanel(new BorderLayout(50,20));
         JPanel fpsSettingsPanel = new JPanel(new FlowLayout());

            JCheckBox chargebarVisible = new JCheckBox("Show chargebar");
            chargebarVisible.setHorizontalAlignment(SwingConstants.CENTER);
            chargebarVisible.setSelected(true);
            chargebarVisible.setFocusable(false);
            chargebarVisible.addActionListener(e -> {
                if(chargebarVisible.isSelected()) {
                    titleScreen.setChargebarVisible(true);
                }
                else {
                    titleScreen.setChargebarVisible(false);
                }
            });


            JLabel fpsLabel = new JLabel("FPS:");
            JSlider fpsSettings = new JSlider(0,120,90);
            fpsSettings.setSnapToTicks(true);
            fpsSettings.setMajorTickSpacing(30);
            fpsSettings.setMinorTickSpacing(5);
            fpsSettings.setPaintTicks(true);
            fpsSettings.setPaintLabels(true);

            fpsSettingsPanel.add(fpsLabel);
            fpsSettingsPanel.add(fpsSettings);

             JComboBox resolutionBox = new JComboBox(resolution);
             resolutionBox.setSelectedIndex(1);
             resolutionBox.setVisible(true);
             JLabel resolutionLabel = new JLabel("    Resolution:");

             resolutionBox.addActionListener(e -> {
                 titleScreen.setSelectedResolution(resolutionBox.getSelectedIndex());
             });

            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(e -> {
                if (fpsSettings.getValue() == 0) {


                    JFrame kkt = new JFrame();
                    kkt.setAlwaysOnTop(true);

                    kkt.setSize(1280,720);
                    setAlwaysOnTop(false);

                    kkt.add(new JLabel(new ImageIcon(this.getClass().getResource("/surak.gif"))), BorderLayout.CENTER);
                    AudioPlayer.playSound("/sounds/surakxd.wav");
                    kkt.setLocationRelativeTo(null);
                    kkt.setVisible(true);

                    Timer timer = new Timer(3300, event -> {
                        kkt.dispose();
                        setAlwaysOnTop(true);
                    });
                    timer.setRepeats(false);
                    timer.start();



                }
                else {
                    dispose();
                }
            });
            panel.add(applyButton, BorderLayout.SOUTH);

            fpsSettings.getModel().addChangeListener(e -> {
                titleScreen.setFps(fpsSettings.getValue());
            });




         panel.add(resolutionBox,BorderLayout.CENTER);
         panel.add(resolutionLabel,BorderLayout.WEST);

         panel.add(fpsSettingsPanel, BorderLayout.NORTH);
         panel.add(new JLabel(""), BorderLayout.EAST);


            add(panel, BorderLayout.CENTER);
            add(chargebarVisible, BorderLayout.NORTH);
         setSize(350, 200);
         setLocationRelativeTo(null);
         setVisible(true);
     }

}
