package Map;

import Audio.AudioPlayer;
import Audio.MusicPlayer;
import Audio.Sound;
import Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Store extends JFrame {

    private Player player;
    private double resolutionMultiplier;
    private ImageIcon image;

    public Store(Player player, double resolutionMultiplier) {
        super("tomino jumper: ich liebe einkaufen");
        this.player = player;
        this.resolutionMultiplier = resolutionMultiplier;
    }

    public void init(){
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);

        try{
            image =  new ImageIcon(this.getClass().getClassLoader().getResource("store.png"));
            image.setImage(image.getImage().getScaledInstance(800,600, Image.SCALE_SMOOTH));
        }catch (NullPointerException e){
            System.out.println("missing file");
        }

        JLabel background = new JLabel(image);
        add(background);

        MusicPlayer.play(AudioPlayer.loadSound("/sounds/redSunInTheSky.wav"));

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MusicPlayer.stop();
            }
        });

        setVisible(true);
    }
}
