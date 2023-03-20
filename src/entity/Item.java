package entity;

import com.google.gson.Gson;
import main.GamePanel;
import tile.MapDto;
import utility.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Item extends GameObject {

    private GamePanel gamePanel;

    public Item(Position mapPosition, GamePanel gamePanel) {
        super(mapPosition.getX(), mapPosition.getY(), 0);

        this.gamePanel = gamePanel;
    }

    public Item readItemData(String itemFilePath) throws IOException {
        Item item;

        try {
            Gson gson = new Gson();
            ItemDto itemInitValues = gson.fromJson(new FileReader(itemFilePath), ItemDto.class);
            item = mapItemDtoToItem(itemInitValues);
        }
        catch(FileNotFoundException ex) {
            System.out.println("[Item] [readItemData]: Unable to get file at " + itemFilePath);
            ex.printStackTrace();
            throw ex;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            throw ex;
        }

        return item;
    }

    public Item mapItemDtoToItem(ItemDto itemDto) throws IOException {
        Item newItem = new Item(new Position(itemDto.mapX, itemDto.mapY), this.gamePanel);
        newItem.setCollisionOn(itemDto.collision);
        for(String imagePath : itemDto.images) {
            newItem.addSprite(ImageIO.read(getClass().getResourceAsStream(imagePath)));
        }

        return newItem;
    }

    @Override
    public void update() {

    }

    @Override
    public void paintComponent(Graphics2D g) {

    }
}
