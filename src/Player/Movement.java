package Player;

import Windows.SettingsScreen;
import Windows.TitleScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener{


    private Player player;

    public Movement( Player p) {
        this.player = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE -> player.setJumping(true);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.setWalkingL(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setWalkingR(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE -> player.setJumping(false);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.setWalkingL(false);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setWalkingR(false);

        }
    }

}


