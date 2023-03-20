package entity;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends GameObject {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel.getTileSize() * 7, gamePanel.getTileSize() * 7, 3);

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        this.sprites = new ArrayList<BufferedImage>();

        getPlayerImage();
        this.setDirection("idle");
        this.setScreenX((gamePanel.getWidth() / 2) - (gamePanel.getTileSize() / 2));
        this.setScreenY((gamePanel.getHeight() / 2) - (gamePanel.getTileSize() / 2));

        this.setCollisionArea(new Rectangle(gamePanel.getTileSize() / 12, gamePanel.getTileSize() / 6 , gamePanel.getTileSize() / 12 * 10, gamePanel.getTileSize() / 12 * 10));
        this.setCollisionOn(true);
    }

    @Override
    public void update() {
        frameCounter++;

        if(frameCounter >= 15) {
            spriteNumber++;

            frameCounter = 0;
        }

        if(keyHandler.upPressed) {
            this.direction = "up";
        }
        else if(keyHandler.downPressed) {
            this.direction = "down";
        }
        else if(keyHandler.leftPressed) {
            this.direction = "left";
        }
        else if(keyHandler.rightPressed) {
            this.direction = "right";
        }
        else {
            this.direction = "idle";
        }

        this.setCollisionOn(false);
        gamePanel.getCollisionChecker().checkTile(this);

        if(!this.isCollisionOn()) {
            movePlayer();
        }
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "idle":
                if(spriteNumber > 3) {
                    spriteNumber = 0;
                }
                image = sprites.get(spriteNumber);
                break;
            case "up":
                if(spriteNumber < 4 || spriteNumber > 7) {
                    spriteNumber = 4;
                }
                image = sprites.get(spriteNumber);
                break;
            case "down":
                if(spriteNumber < 8 || spriteNumber > 11) {
                    spriteNumber = 8;
                }
                image = sprites.get(spriteNumber);
                break;
            case "left":
                if(spriteNumber < 12 || spriteNumber > 15) {
                    spriteNumber = 12;
                }
                image = sprites.get(spriteNumber);
                break;
            case "right":
                if(spriteNumber < 16 || spriteNumber > 19) {
                    spriteNumber = 16;
                }
                image = sprites.get(spriteNumber);
                break;
        }

        g2.drawImage(image, this.screenX, this.screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    private void getPlayerImage() {
        try {
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_0.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_1.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_2.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/Idle/idle_3.png")));

            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkUp/up_0.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkUp/up_1.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkUp/up_2.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkUp/up_3.png")));

            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkDown/down_0.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkDown/down_1.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkDown/down_2.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkDown/down_3.png")));

            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkLeft/left_0.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkLeft/left_1.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkLeft/left_2.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkLeft/left_3.png")));

            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkRight/right_0.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkRight/right_1.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkRight/right_2.png")));
            addSprite(ImageIO.read(getClass().getResourceAsStream("/player/WalkRight/right_3.png")));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void movePlayer() {
        switch(this.getDirection()) {
            case "up":
                this.mapY -= this.speed;
                break;
            case "down":
                this.mapY += this.speed;
                break;
            case "left":
                this.mapX -= this.speed;
                break;
            case "right":
                this.mapX += this.speed;
                break;
        }
    }
}
