package Player;

import Map.Store;
import Windows.SettingsScreen;
import Windows.TitleScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
        if(e.getKeyCode() == storeKeyEvents[key]){
            player.openStore();
            key = new Random().nextInt(storeKeyEvents.length);
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W, KeyEvent.VK_SPACE -> {
                if(!player.isHadJumped()) player.setJumping(true);
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A  -> player.setWalkingL(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> player.setWalkingR(true);
            case KeyEvent.VK_I -> player.openInventory();
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


