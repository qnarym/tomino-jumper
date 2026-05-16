package Windows;

import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends JFrame {

    private TitleScreen titleScreen;

     public SettingsScreen(TitleScreen titleScreen) {
         super("tomino jumper: settings");
         this.titleScreen = titleScreen;

     }

     public void init() {
         setResizable(false);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         setLocationRelativeTo(null);
         setLayout(new BorderLayout());
         setAlwaysOnTop(true);

         JPanel panel = new JPanel();

            JSlider fpsSettings = new JSlider(0,90,90);
            panel.add(fpsSettings);

            fpsSettings.getModel().addChangeListener(e -> {
                titleScreen.setFps(fpsSettings.getValue());
            });



            add(panel, BorderLayout.NORTH);
         setSize(400, 300);
         setVisible(true);
     }

}
