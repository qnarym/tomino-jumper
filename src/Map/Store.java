package Map;

import Audio.AudioPlayer;
import Audio.MusicPlayer;
import Audio.Sound;
import Player.Player;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Store extends JFrame {

    private Player player;
    private double resolutionMultiplier;

    public Store(Player player, double resolutionMultiplier) {
        super("tomino jumper: ich liebe einkaufen");
        this.player = player;
        this.resolutionMultiplier = resolutionMultiplier;
    }

    public void init(){
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);

        MusicPlayer.play(AudioPlayer.loadSound("/sounds/redSunInTheSky.wav"));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MusicPlayer.stop();
            }
        });

        setVisible(true);
    }
}
