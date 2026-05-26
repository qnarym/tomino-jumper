package Player;

import Audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinClicker extends JFrame {

    private Player player;
    private ImageIcon image;
    private double dallasMade = 0;

    public CoinClicker(Player player) {
        super("tomino jumper: printing dallas");
        this.player = player;
    }

    public void init(){
        setSize(500,500);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JButton coin = new JButton();
        coin.setFocusable(false);
        coin.setLayout(new BorderLayout());
        coin.setBackground(new Color(0,0,0));
        coin.setBorderPainted(false);
        UIManager.put("Button.select",new Color(30, 30, 30));


        try{
            image =  new ImageIcon(this.getClass().getClassLoader().getResource("dallaone.gif"));
        }catch (NullPointerException e){
            System.out.println("missing file");
        }
        coin.setIcon(new ImageIcon(image.getImage()));

        JLabel coinLabel = new JLabel("make dallas");
        coinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        coinLabel.setVerticalAlignment(SwingConstants.CENTER);
        coinLabel.setFont(new Font("Arial", Font.BOLD, 50));
        coin.add(coinLabel,BorderLayout.SOUTH);

        JLabel coinStatus = new JLabel();
        coinStatus.setForeground(new Color(255, 255, 255));
        coinStatus.setHorizontalAlignment(SwingConstants.CENTER);
        coinStatus.setVerticalAlignment(SwingConstants.CENTER);
        coinStatus.setFont(new Font("Arial", Font.BOLD, 30));
        coin.add(coinStatus,BorderLayout.NORTH);

        coin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setCoins(player.getCoins()+0.05);
                System.out.print("\rdalla made: "+player.getCoins());

                AudioPlayer.playSound("/sounds/dallamade.wav");
            }
        });
        add(coin);
        setVisible(true);

    }
}
