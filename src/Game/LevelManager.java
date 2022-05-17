package Game;

import Tiles.TileManager;
import Entity.*;

import java.awt.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelManager {
    int level = 1;
    GamePanel gamePanel;
    TileManager tileManager;
    KeyHandler keyHandler;
    Player player;
    ArrayList<Mob> mobArr;

    int nrMobi;
    LevelManager(GamePanel p_gamePanel, TileManager p_tileManager,  KeyHandler p_keyHandler, Player p_player) {
        gamePanel = p_gamePanel;
        tileManager = p_tileManager;
        keyHandler = p_keyHandler;
        player = p_player;
        mobArr = new ArrayList<>();

    }

    public void loadLevel(Graphics2D g) {
        try {

            String mapPath = "/res/Level/Level_" + level + ".txt";
            InputStream file = getClass().getResourceAsStream(mapPath);
            assert file != null;
            Scanner scanner = new Scanner(file);
            nrMobi = scanner.nextInt();
            for(int i = 0; i < nrMobi; i++) {
                mobArr.add(new Mob(gamePanel, player, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
            }

            tileManager.loadMap(g, mapPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }


        // In fiecare fisier "Level_xx.txt" sa fie dat viteza, numarul si spawnPoint-ul mobilor, cat si spawnpoint-ul player-ului
        // Trecerea de la un nivel la altul.
        // Tile-uri care omoara mobi (gropi).
        // Integrarea cu baza de date
        // Sprite urile mobilor
        // Cv animatie gen de wasted

    }
    public void update(Graphics2D g) {
        if(gamePanel.gameState == gamePanel.playState) {
            try {
                for (int i = 0; i < nrMobi; i++) {
                    mobArr.get(i).update(g);
                    player.update();
                }
            } catch (Exception e) {

            }

        }


    }
}
