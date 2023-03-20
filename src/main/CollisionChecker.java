package main;

import entity.GameObject;
import tile.Tile;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(GameObject gameObject) {
        int gameObjectLeftWorldX = gameObject.getMapX() + gameObject.getCollisionArea().x;
        int gameObjectRightWorldX = gameObject.getMapX() + gameObject.getCollisionArea().x + gameObject.getCollisionArea().width;
        int gameObjectTopWorldY = gameObject.getMapY() + gameObject.getCollisionArea().y;
        int gameObjectBottomWorldY = gameObject.getMapY() + gameObject.getCollisionArea().y + gameObject.getCollisionArea().height;

        int gameObjectLeftCol = gameObjectLeftWorldX / this.gamePanel.getTileSize();
        int gameObjectRightCol = gameObjectRightWorldX / this.gamePanel.getTileSize();
        int gameObjectTopRow = gameObjectTopWorldY / this.gamePanel.getTileSize();
        int gameObjectBottomRow = gameObjectBottomWorldY / this.gamePanel.getTileSize();

        Tile tile1, tile2;
        switch(gameObject.getDirection()) {
            case "up":
                gameObjectTopRow = (gameObjectTopWorldY - gameObject.getSpeed()) / this.gamePanel.getTileSize();
                tile1 = this.gamePanel.getMap().getTiles()[gameObjectTopRow][gameObjectLeftCol];
                tile2 = this.gamePanel.getMap().getTiles()[gameObjectTopRow][gameObjectRightCol];

                if(tile1.isCollisionOn() || tile2.isCollisionOn()) {
                    gameObject.setCollisionOn(true);
                }
                break;
            case "down":
                gameObjectBottomRow = (gameObjectBottomWorldY - gameObject.getSpeed()) / this.gamePanel.getTileSize();
                tile1 = this.gamePanel.getMap().getTiles()[gameObjectBottomRow][gameObjectLeftCol];
                tile2 = this.gamePanel.getMap().getTiles()[gameObjectBottomRow][gameObjectRightCol];

                if(tile1.isCollisionOn() || tile2.isCollisionOn()) {
                    gameObject.setCollisionOn(true);
                }
                break;
            case "left":
                gameObjectLeftCol = (gameObjectLeftWorldX - gameObject.getSpeed()) / this.gamePanel.getTileSize();
                tile1 = this.gamePanel.getMap().getTiles()[gameObjectTopRow][gameObjectLeftCol];
                tile2 = this.gamePanel.getMap().getTiles()[gameObjectBottomRow][gameObjectLeftCol];

                if(tile1.isCollisionOn() || tile2.isCollisionOn()) {
                    gameObject.setCollisionOn(true);
                }
                break;
            case "right":
                gameObjectRightCol = (gameObjectRightWorldX - gameObject.getSpeed()) / this.gamePanel.getTileSize();
                tile1 = this.gamePanel.getMap().getTiles()[gameObjectTopRow][gameObjectRightCol];
                tile2 = this.gamePanel.getMap().getTiles()[gameObjectBottomRow][gameObjectRightCol];

                if(tile1.isCollisionOn() || tile2.isCollisionOn()) {
                    gameObject.setCollisionOn(true);
                }
                break;
        }
    }
}
