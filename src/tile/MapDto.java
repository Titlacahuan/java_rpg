package tile;

public class MapDto {
    TileDto[][] tileMap;

    public MapDto(int rows, int columns) {
        tileMap = new TileDto[rows][columns];
    }
}
