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
            if(player.worldY < 40) {
                level++;
            }
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
    }
    public void update() {
        if(gamePanel.gameState == gamePanel.playState) {
            updateMobs();
            player.update();
        }
        nextLvl();
    }

    public void updateMobs() {
        try {
            for(int i = 0; i < nrMobi; i++) {
                mobArr.get(i).update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void drawMobs(Graphics2D g) {
        try {
            for (int i = 0; i < nrMobi; i++) {
                mobArr.get(i).draw(g);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {
        player.draw(g);
        drawMobs(g);
    }

    public boolean nextLvl() {
        if(player.worldY < 38) {
            player.setSpawnPoint(100, 100, 4);
            level++;

            mobArr = new ArrayList<>();
            return true;
        }
        return false;

    }
}
