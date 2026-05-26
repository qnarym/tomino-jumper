package Player;

import Windows.JCustoms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

public class Inventory extends JFrame {

    private Player player;
    private double resolutionMultiplier;
    private CoinClicker coinClicker;
    private ImageIcon image;


    public Inventory(Player player, double resolutionMultiplier) {
        super("tomino jumper: inventing inventory");
        this.player = player;
        this.resolutionMultiplier = resolutionMultiplier;
        coinClicker = new CoinClicker(player);
    }

    public void init(){
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,600);

        try{
        image =  new ImageIcon(this.getClass().getClassLoader().getResource("inventory.png"));
        image.setImage(image.getImage().getScaledInstance(800,600,Image.SCALE_SMOOTH));
        }catch (NullPointerException e){
        System.out.println("missing file");
        }

        JLabel inventoryBackground = new JLabel(image);
        inventoryBackground.setLayout(null);

        try{
            image =  new ImageIcon(this.getClass().getClassLoader().getResource("onedalla.gif"));
        }catch (NullPointerException e){
            System.out.println("missing file");
        }

        JLabel dallasIcon = new JLabel(image);
        dallasIcon.setOpaque(false);
        JLabel dallas = new JLabel();
        dallas.setText(player.getCoins()+"");
        dallas.setFont(new Font("Arial", Font.BOLD, 40));
        dallas.setForeground(new Color(255, 255, 255));

        JButton makeDallas = new JButton("print dallas");
        JCustoms.inventoryButton(makeDallas);
        makeDallas.setSize(new Dimension(100,20));
        makeDallas.setFocusable(false);
        makeDallas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                coinClicker.init();
            }
        });

        dallasIcon.setBounds(10,450,80,80);
        dallas.setBounds(100,450,120,80);
        makeDallas.setBounds(50,515,100,40);

        inventoryBackground.add(dallasIcon);
        inventoryBackground.add(makeDallas);
        inventoryBackground.add(dallas);

        add(inventoryBackground);

        addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                BigDecimal bd = new BigDecimal(player.getCoins());
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);

                dallas.setText(bd+"");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
