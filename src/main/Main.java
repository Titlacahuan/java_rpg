package main;

public class Main {

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        Window window = new Window("Pocket Monsters", gamePanel);

        gamePanel.start();
    }
}