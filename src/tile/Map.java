package tile;

import com.google.gson.Gson;
import main.GamePanel;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Map {
    Tile[][] tiles;

    GamePanel gamePanel;

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void readMapData(String mapFilePath) throws FileNotFoundException {
        Gson gson = new Gson();
        MapDto tileInitValues = gson.fromJson(new FileReader(mapFilePath), MapDto.class);

        tiles = new Tile[tileInitValues.tileMap.length][];

        for(int i = 0; i < tileInitValues.tileMap.length; i++) {
            tiles[i] = new Tile[tileInitValues.tileMap[i].length];

            for(int j = 0; j < tileInitValues.tileMap[i].length; j++) {
                int index = tileInitValues.tileMap[i][j].tileIndex;
                tiles[i][j] = new Tile(j * this.gamePanel.getTileSize(), i * this.gamePanel.getTileSize(), this.gamePanel);
                tiles[i][j].addSprite(gamePanel.getTileMap()[index]);
                tiles[i][j].setCollisionOn(tileInitValues.tileMap[i][j].collision);
            }
        }
    }
    public Tile[][] getTiles() {
        return this.tiles;
    }
}
