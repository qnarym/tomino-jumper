package Player;

import Map.Levels;


public class Player {

    private int x;
    private int y;

    private final String[] playerAnimation =  {"res/character/standingR.png","res/character/standingL.png","res/character/walkingR.png","res/character/walkingL.png","res/character/jumpChargingR.png","res/character/jumpChargingL.png","res/character/fallingR.png","res/character/fallingL.png"};
    private int status = 0;
    private String dir = "";

    private int currLevel = 0;
    private Levels level = new Levels(currLevel);

    private boolean isFalling;
    private int fallingSpeed;

    private boolean isWalkingR;
    private boolean isWalkingL;
    private int velocity = 0;

    private boolean isJumping;
    private int jumpForce;

    private int jumpForceR;
    private int jumpForceL;

    public String getPlayerAnimation(int status) {
        return playerAnimation[status];
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public int getFallingSpeed() {
        return fallingSpeed;
    }

    public void setFallingSpeed(int fallingSpeed) {
        this.fallingSpeed = fallingSpeed;
    }

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
