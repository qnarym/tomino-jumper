package Map;

import java.util.ArrayList;

public class Levels {

    private int currLevel;
    private String[] levelMap = {"res/levels/level11.png","res/levels/level12.png"};



    public Levels(int currLevel) {
        this.currLevel = currLevel;
    }


    public String getLevel(int currLevel){
        return levelMap[currLevel];
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

}
