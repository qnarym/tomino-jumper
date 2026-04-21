import Windows.TitleScreen;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        TitleScreen ts = new TitleScreen();

        ts.init();


    }
}