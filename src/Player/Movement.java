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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE -> {
                if(!player.isHadJumped()) player.setJumping(true);
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.setWalkingL(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setWalkingR(true);
            case KeyEvent.VK_Q -> player.setReadSign(true);
            case KeyEvent.VK_B -> {
                try {
                    Desktop.getDesktop().browse(new URI("https://docs.google.com/forms/d/e/1FAIpQLSdhzikw8zP-5yTDZM-Q36Mvle3b5o5eXJShwR5cS4YXCn_ieQ/viewform"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
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


