package Windows;

import javax.swing.*;
import java.awt.*;

public class JCustoms {

    public static void titleScreenButton(JButton button){
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(150,50));

        button.setFocusPainted(true);
        button.setBorderPainted(true);
    }

    public static void inventoryButton(JButton button){
        button.setBackground(new Color(0,0,0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    public static void setBackground(JButton button){
        button.setBackground(new Color(0, 0, 0, 50));
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(20,20));
    }


}
