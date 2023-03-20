package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import tile.Map;
import tile.Tile;
//import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    @Serial
    private static final long serialVersionUID = -7098198471728515643L;

    final int ORIGINAL_TILE_SIZE = 16;
    final int SCALE = 3;

    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 9;

    final int MAX_MAP_COL = 32;
    final int MAX_MAP_ROW = 32;

    final int WIDTH = TILE_SIZE * MAX_SCREEN_COL;
    final int HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    final int WORLD_WIDTH = TILE_SIZE * MAX_MAP_COL;
    final int WORLD_HEIGHT = TILE_SIZE * MAX_MAP_ROW;

    final int MAX_FPS = 60;

    Thread gameThread;

    KeyHandler keyHandler;

    Player player;

    CollisionChecker collisionChecker;

    // This is the actual full image tile set
    BufferedImage atlas;

    // This is the array of every tile from the atlas
    BufferedImage[] tileMap;

    // This is the actual map of the game
    Map map;


    public GamePanel() {
        keyHandler = new KeyHandler();
        player = new Player(this, keyHandler);
        collisionChecker = new CollisionChecker(this);
        map = new Map(this);

        try {
            readAtlas("/tile/retro.png");
            fillTileMap(this);
            map.readMapData("src/res/map/map1.json");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        this.setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public int getOriginalTileSize() {
        return this.ORIGINAL_TILE_SIZE;
    }
    public int getTileSize() {
        return this.TILE_SIZE;
    }

    public int getWidth() {
        return this.WIDTH;
    }

    public int getHeight() {
        return this.HEIGHT;
    }

    public int getMaxScreenColumn() {
        return this.MAX_SCREEN_COL;
    }

    public int getMaxScreenRow() {
        return this.MAX_SCREEN_ROW;
    }

    public CollisionChecker getCollisionChecker() {
        return this.collisionChecker;
    }

    public BufferedImage[] getTileMap() {
        return tileMap;
    }

    public Map getMap() {
        return this.map;
    }

    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        try {
            gameThread.join();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long currentTime = 0;
        double tickTime = 1000000000 / MAX_FPS;
        double delta = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / tickTime;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();

        for(int i = 0; i < map.getTiles().length; i++) {
            for(int j = 0; j < map.getTiles()[i].length; j++) {
                map.getTiles()[i][j].update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < map.getTiles().length; i++) {
            for(int j = 0; j < map.getTiles()[i].length; j++) {
                Tile tile = map.getTiles()[i][j];

                int screenX = tile.getMapX() - player.getMapX() + player.getScreenX();
                int screenY = tile.getMapY() - player.getMapY() + player.getScreenY();
                int buffer = 2 * TILE_SIZE;

                if(tile.getMapX() > player.getMapX() - player.getScreenX() - buffer &&
                    tile.getMapX() < player.getMapX() + player.getScreenX() + buffer &&
                    tile.getMapY() > player.getMapY() - player.getScreenY() - buffer &&
                    tile.getMapY() < player.getMapY() + player.getScreenY() + buffer) {

                    tile.setScreenPosition(screenX, screenY);
                    tile.paintComponent(g2);
                }
            }
        }

        player.paintComponent(g2);

        g2.dispose();
    }

    public void readAtlas(String path) {
        try {
            atlas = ImageIO.read(getClass().getResourceAsStream(path));
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void fillTileMap(GamePanel gamePanel) {
        final int originalTileSize = gamePanel.getOriginalTileSize();

        int atlasWidth = atlas.getWidth();
        int atlasHeight = atlas.getHeight();

        int numberOfRows = atlasHeight / originalTileSize;
        int numberOfColumns = atlasWidth / originalTileSize;

        int indexCounter = 0;

        tileMap = new BufferedImage[numberOfRows * numberOfColumns];

        for(int i = 0; i < numberOfRows; i++) {
            for(int j = 0; j < numberOfColumns; j++) {
                tileMap[indexCounter] = atlas.getSubimage(j * originalTileSize, i * originalTileSize, originalTileSize, originalTileSize);
                indexCounter++;
            }
        }
    }
}