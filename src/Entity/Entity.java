package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // Pozitia entitatii pe harta
    public int worldX, worldY;
    public int speed;

    // Variabilele in care urmeaza a fi incarcate sprite-urile entitatii
    public BufferedImage up1, up2, up3, up4;
    public BufferedImage down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public String direction;

    // spriteCounter tine evidenta numarului de frame-uri care a trecut de cand s-a schimbat ultima data sprite-ul
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // solidArea este hitbox-ul entitatii
    public Rectangle solidArea;

    public boolean collisionOn  = false;
}
