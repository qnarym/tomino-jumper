package Player;

import Audio.AudioPlayer;
import Audio.MusicPlayer;
import Map.*;

import java.awt.*;


public class Player {

    private int x;
    private int y;

    private final String[] playerAnimation =  {"character/standingR.png","character/standingL.png","character/walkingR.png","character/walkingL.png","character/jumpChargingR.png","character/jumpChargingL.png","character/fallingR.png","character/fallingL.png"};
    private int status = 0;
    private String dir = "";
    private Dimension dimension;

    private Store store;
    private Inventory inventory;



    public Player(int x, int y, Dimension dihmension) {
        this.x = x;
        this.y = y;
        this.dimension = dihmension;
        double resolutionMultiplier = dimension.getWidth()/1600;
        System.out.println(dimension);
        level = new Levels(currLevel, dimension, resolutionMultiplier);

       store = new Store(this, resolutionMultiplier);
       inventory = new Inventory(this, resolutionMultiplier);
    }

    public void openStore(){
        store.init();
    }
    public void openInventory(){
        inventory.init();
    }

    private int currLevel = 0;
    private Levels level;

    private double coins = 0;

    private boolean isFalling;
    private double fallingSpeed;

    private boolean isWalkingR;
    private boolean isWalkingL;
    private double velocity = 0;

    private boolean isJumping;
    private double jumpForce;

    private double jumpForceR;
    private double jumpForceL;

    public String getPlayerAnimation(int status) {
        return playerAnimation[status];
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
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

    public double getFallingSpeed() {
        return fallingSpeed;
    }

    public void setFallingSpeed(double fallingSpeed) {
        this.fallingSpeed = fallingSpeed;
    }

    public double getJumpForce() {
        return jumpForce;
    }

    public void setJumpForce(double jumpForce) {
        this.jumpForce = jumpForce;
    }

    public double getJumpForceR() {
        return jumpForceR;
    }

    public void setJumpForceR(double jumpForceR) {
        this.jumpForceR = jumpForceR;
    }

    public double getJumpForceL() {
        return jumpForceL;
    }

    public void setJumpForceL(double jumpForceL) {
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
