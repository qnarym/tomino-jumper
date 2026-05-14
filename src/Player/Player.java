package Player;

import java.awt.event.KeyListener;

public class Player {

    private int x;
    private int y;


    private boolean isWalkingR;
    private boolean isWalkingL;

    private boolean isJumping;
    private int jumpForce;

    private int jumpForceR;
    private int jumpForceL;


    public int getJumpForce() {
        return jumpForce;
    }

    public void setJumpForce(int jumpForce) {
        this.jumpForce = jumpForce;
    }

    public int getJumpForceR() {
        return jumpForceR;
    }

    public void setJumpForceR(int jumpForceR) {
        this.jumpForceR = jumpForceR;
    }

    public int getJumpForceL() {
        return jumpForceL;
    }

    public void setJumpForceL(int jumpForceL) {
        this.jumpForceL = jumpForceL;
    }

    public boolean isWalkingR() {
        return isWalkingR;
    }

    public void setWalkingR(boolean walkingR) {
        isWalkingR = walkingR;
    }

    public boolean isWalkingL() {
        return isWalkingL;
    }

    public void setWalkingL(boolean walkingL) {
        isWalkingL = walkingL;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
