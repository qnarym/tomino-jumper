package Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

/**
 * Customized keylistener
 */
public class Movement implements KeyListener{


    private Player player;

    private final int[] storeKeyEvents = {KeyEvent.VK_U, KeyEvent.VK_T, KeyEvent.VK_Z, KeyEvent.VK_R};
    private int key = 0;

    public Movement( Player p) {
        this.player = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE -> player.setJumping(false);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.setWalkingL(false);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setWalkingR(false);
            case KeyEvent.VK_Q -> player.setReadSign(false);
        }
    }

}


