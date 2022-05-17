package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    private static MouseHandler instance = null;
    GamePanel gamePanel;

    static Point mousePress;
    static Point mouseRelease;
    private MouseHandler(GamePanel p_gamePanel) {
        gamePanel = p_gamePanel;
        mousePress = new Point();
        mouseRelease = new Point();
    }

    public static MouseHandler getInstance(GamePanel p_gamePanel) {
        // Instanțierea MouseHandler-ului (Singleton)
        if(instance == null) {
            instance = new MouseHandler(p_gamePanel);
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePress = e.getLocationOnScreen();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseRelease = e.getLocationOnScreen();
    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {


    }

    // Funcția getCoord() returnează punctele in care mouse-ul a fost apăsat, respectiv eliberat
    public static Point[] getCoord() {
        Point[] mouseClick = new Point[2];
        mouseClick[0] = mousePress;
        mouseClick[1] = mouseRelease;
        return mouseClick;
    }
    public static void resetCoord() {
        mousePress.move(0, 0);
        mouseRelease.move(0, 0);
    }

}
