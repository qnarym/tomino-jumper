package Player;

import javax.swing.*;
import java.awt.*;

public class Inventory extends JFrame {

    private Player player;
    private double resolutionMultiplier;

    public Inventory(Player player, double resolutionMultiplier) {
        super("tomino jumper: inventing inventory");
        this.player = player;
        this.resolutionMultiplier = resolutionMultiplier;
    }

    public void init(){
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);



        setVisible(true);
    }
}
