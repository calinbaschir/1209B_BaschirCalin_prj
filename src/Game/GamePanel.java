package Game;

import Entity.Player;
import Entity.Mob;
import Tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    final int WIDTH = 10 * tileSize;
    final int HEIGHT = 10 * tileSize;
    final int fps = 60;
    Graphics g;

    // Variabile pentru a stabili stadiul jocului
    public final int titleState = 0;
    public final int playState = 1;
//    public final int pauseState = 2;
//    public final int exitState = -1;
    public int gameState = titleState;


    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();

    // Caracteristicile ferestrei
    Thread gameThread;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    Player player = new Player(this, keyHandler);
    LevelManager levelManager = new LevelManager(this, tileManager, keyHandler, player);
    Menu menu;

    public GamePanel() {

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(MouseHandler.getInstance(this));
        this.setFocusable(true);

        try {
            menu = new Menu(this, keyHandler, MouseHandler.getInstance(this), player);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double drawInterval = (double) 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            update();
            repaint();
            // De fiecare data când se desenează pe ecran se calculează următorul moment in care ar trebui sa se repete
            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }

    }

    public void update() {
        Graphics2D g2 = (Graphics2D) g;
        player.update();
        levelManager.update(g2);
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        levelManager.loadLevel(g2);
        levelManager.update(g2);

        // In funcție de gameState se va decide ce anume sa se deseneze

        if(gameState == playState) {
            // Player-ul va apărea doar in timp ce jocul rulează

            player.draw(g2);
            if(player.isDead()) {
                gameState = titleState;
                levelManager.update(g2);
            }
        } else if(gameState == titleState ){
            menu.draw(g2);
        }
        g2.dispose();
    }

}
