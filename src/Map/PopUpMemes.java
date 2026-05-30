package Map;

import Audio.AudioPlayer;
import Audio.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class PopUpMemes extends JFrame {

    private final JPanel contentPane;
    private ImageIcon imageIcon;
    private JLabel meme;

    private boolean popUpshowed = false;
    private int currLevel;

    private final String[] popUpMemes = {"memes/krecek.png","memes/johnroblox.gif","memes/vypecky.gif","memes/maow.gif","memes/fatboie.gif","memes/twoguys.gif","memes/cuh-guh.gif"};
    private final String[] popUpAudio = {"/memes/pew.wav","/memes/johnroblox.wav","/memes/vypecky.wav","/memes/maow.wav","/memes/fatboie.wav","/memes/twoguys.wav","/memes/hit.wav"};
    private final int[] popUpLength = {700,5000,192000,3700,6600,2300,1200};

    public PopUpMemes(){
        super("tomino jumper: meme time dallas");
        setSize(800,600);

        contentPane = new JPanel();
    }

    public void init(){
        if(!popUpshowed){
            popUpshowed = true;
            int random = new Random().nextInt(popUpMemes.length);
            if (currLevel!=5 && random == 2){
                random--;
            }
            if(currLevel==5){
                random = 2;
            }

            try{
                imageIcon =  new ImageIcon(this.getClass().getClassLoader().getResource(popUpMemes[random]));
                MusicPlayer.play(AudioPlayer.loadSound(popUpAudio[random]));
                meme = new JLabel(imageIcon);
            }catch (NullPointerException e){
                System.out.println("missing file");
            }



            contentPane.removeAll();
            contentPane.add(meme);
            contentPane.setFocusable(false);
            add(contentPane);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    MusicPlayer.stop();
                }
            });

            pack();
            setFocusable(false);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);

            Timer timer = new Timer(popUpLength[random], event -> {
                dispose();
                setAlwaysOnTop(true);
                MusicPlayer.stop();
                popUpshowed = false;
            });
            timer.setRepeats(false);
            timer.start();

        }
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

}
