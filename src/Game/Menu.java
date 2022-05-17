package Game;


import Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class Menu {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    MouseHandler mouseHandler;
    BufferedImage trapped, playButton;
    BufferedImage playButton_pressed;
    Player player;
    // BufferedImage exitButton, exitButton_pressed;

    // Un vector de doua coordonate, date in felul următor:
    // coord[0] = Coordonatele cursorului la momentul apăsării mouse-ului
    // coord[1] = coordonatele cursorului la momentul eliberării mouse-ului
    Point[] coord;

    public Menu(GamePanel p_gamePanel, KeyHandler p_KeyHandler, MouseHandler p_mouseHandler, Player p_player)  {
        gamePanel = p_gamePanel;
        keyHandler = p_KeyHandler;
        mouseHandler = p_mouseHandler;
        player = p_player;
    }
    private void getMenuImg() {
        try {
            trapped = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/TRAPPED.png")));
            playButton = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/PLAY.png")));
            //exitButton  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/EXIT.png")));

            playButton_pressed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/PLAY_P.png")));
            //exitButton_pressed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Menu/EXIT_P.png")));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void draw(Graphics2D g) {
        getMenuImg();
        g.drawImage(trapped, 120, 100, 402, 65, null);
        g.drawImage(playButton, 250, 200, 112, 39, null);
        //g.drawImage(exitButton, 247, 250, 116, 39, null);

        coord = MouseHandler.getCoord();

        // Se verifica daca utilizatorul da click si eliberează mouse-ul pe același buton
        if(coord[0].getX() >= 705 && coord[0].getX() <= 805 && coord[0].getY() >= 310 && coord[0].getY() <= 335) {

            // Este evidențiată selectarea butonului "PLAY"
            g.drawImage(playButton_pressed, 250, 200, 112, 39, null);
            if (coord[1].getX() >= 705 && coord[1].getX() <= 805 && coord[1].getY() >= 310 && coord[1].getY() <= 335) {
                gamePanel.gameState = gamePanel.playState;
                player.ressurect();
                MouseHandler.resetCoord();
            }
        }
    }

}
