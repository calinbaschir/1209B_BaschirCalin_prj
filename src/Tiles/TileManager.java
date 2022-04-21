package Tiles;
import Game.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTile;

    public TileManager(GamePanel p_gamePanel) {
        gamePanel = p_gamePanel;
        tile = new Tile[6];
        mapTile = new int[20][20];
        getTileImage();
    }
    public void getTileImage() {
        try {
            for(int i = 0; i < 6; i++) {
                tile[i] = new Tile();
            }
            // Tile-uri fara coliziune
            tile[0].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Bg_Tile_1.png")));
            tile[1].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Bg_Tile_2.png")));
            tile[2].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Bg_Tile_3.png")));

            // Tile-uri cu coliziune
            tile[3].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Wall_Tile_1.png")));
            tile[3].collision = true;
            tile[4].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Wall_Tile_2.png")));
            tile[4].collision = true;
            tile[5].img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream
                    ("/res/Tiles/Wall_Tile_3.png")));
            tile[5].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(Graphics2D g) {
        // In "/Level/Level_01.txt" se afla o matrice 20x20. Fiecare număr din acea matrice va afișa pe fereastra
        // tile-ul corespunzător din vectorul tile la coordonatele sale.

        if(gamePanel.gameState != gamePanel.titleState) {
            try {

                InputStream file = getClass().getResourceAsStream("/Level/Level_01.txt");
                assert file != null;
                Scanner scanner = new Scanner(new InputStreamReader(file));
                for(int i = 0; i < 20; i++) {
                    String line = scanner.nextLine();
                    String[] numbers = line.split(" ");
                    for(int j = 0; j < 20; j++) {
                        g.drawImage(tile[Integer.parseInt(numbers[j])].img, i * 32, j * 32,
                                gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
                        mapTile[i][j] = Integer.parseInt(numbers[j]);
                    }
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                InputStream file = getClass().getResourceAsStream("/Level/Meniu");
                assert file != null;
                Scanner scanner = new Scanner(new InputStreamReader(file));
                for(int i = 0; i < 20; i++) {
                    String line = scanner.nextLine();
                    String[] numbers = line.split(" ");
                    for(int j = 0; j < 20; j++) {
                        g.drawImage(tile[Integer.parseInt(numbers[j])].img, i * 32, j * 32,
                                gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
                        mapTile[i][j] = Integer.parseInt(numbers[j]);
                    }
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void draw(Graphics2D g) {
        loadMap(g);
    }
}
