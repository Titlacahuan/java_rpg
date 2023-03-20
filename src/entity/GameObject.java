package entity;

import utility.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameObject {
    protected int mapX;
    protected int mapY;
//    protected Position mapPostion;
//    protected Position screenPosition;

    protected int screenX;
    protected int screenY;
    protected int speed;

    protected String direction;

    protected Rectangle collisionArea;
    protected boolean collisionOn;

    protected int frameCounter;

    protected ArrayList<BufferedImage> sprites;
    protected int spriteNumber;

    public GameObject(int mapX, int mapY, int speed) {
        this.mapX = mapX;
        this.mapY = mapY;

        this.screenX = mapX;
        this.screenY = mapY;
//        this.mapPostion = mapPosition;
//        this.screenPosition = mapPosition;
        this.speed = speed;

        this.frameCounter = 0;
        this.spriteNumber = 0;
    }

    public abstract void update();

    public abstract void paintComponent(Graphics2D g);

    public int getMapX() {
        return this.mapX;
    }

    public void setMapX(int value) {
        this.mapX = value;
    }

    public int getMapY() {
        return this.mapY;
    }

    public void setMapY(int value) {
        this.mapY = value;
    }

    public int getScreenX() { return this.screenX; }

    public void setScreenX(int value) {
        this.screenX = value;
    }

    public int getScreenY() { return this.screenY; }

    public void setScreenY(int value) {
        this.screenY = value;
    }

    public void setScreenPosition(int xValue, int yValue) {
        this.screenX = xValue;
        this.screenY = yValue;
    }

//    public Position getMapPostion() {
//        return this.mapPostion;
//    }
//
//    public void updateMapPosition(int x, int y) {
//        this.mapPostion.setPosition(x, y);
//    }
//
//    public Position getScreenPosition() {
//        return this.screenPosition;
//    }
//
//    public void updateScreenPosition(int x, int y) {
//        this.screenPosition.setPosition(x, y);
//    }



    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int value) {

        if(value < 0) {
            this.speed = 0;
        }
        else {
            this.speed = value;
        }
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String value) {
        if(value.isBlank()) {
            this.direction = "idle";
        }
        else {
            this.direction = value;
        }
    }

    public Rectangle getCollisionArea() {
        return this.collisionArea;
    }

    public void setCollisionArea(Rectangle value) {
        this.collisionArea = value;
    }

    public boolean isCollisionOn() {
        return this.collisionOn;
    }

    public void setCollisionOn(boolean value) {
        this.collisionOn = value;
    }

    public int getFrameCounter() {
        return this.frameCounter;
    }

    public void setFrameCounter(int value) {

        if(value < 0) {
            this.frameCounter = 30;
        }
        else {
            this.frameCounter = value;
        }
    }

    public int getSpriteNumber() {
        return this.spriteNumber;
    }

    public void setSpriteNumber(int value) {
        if(value < 0) {
            this.spriteNumber = 0;
        }
        else {
            this.spriteNumber = value;
        }
    }

    public void addSprite(BufferedImage image) {
        if(this.sprites != null) {
            this.sprites.add(image);
        }
    }

    public BufferedImage getTileSprite() {
        return this.sprites.get(spriteNumber);
    }
}
