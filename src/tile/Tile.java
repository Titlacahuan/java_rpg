package tile;

import entity.GameObject;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile extends GameObject {

    private GamePanel gamePanel;

    public Tile(int mapX, int mapY, GamePanel gamePanel) {
        super(mapX, mapY, 0);

        this.gamePanel = gamePanel;
        this.sprites = new ArrayList<BufferedImage>();

        this.setCollisionArea(new Rectangle(0, 0 , gamePanel.getTileSize(), gamePanel.getTileSize()));
    }

    @Override
    public void update() {
        if(frameCounter == 0 || frameCounter >= 90) {
            spriteNumber++;

            if(spriteNumber > sprites.size() - 1) {
                spriteNumber = 0;
            }

            if(frameCounter >= 90) {
                frameCounter = 0;
            }
        }

        frameCounter++;
    }

    @Override
    public void paintComponent(Graphics2D g) {
        g.drawImage(getTileSprite(), screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}
