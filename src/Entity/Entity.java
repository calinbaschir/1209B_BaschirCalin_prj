package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // Poziția entității pe harta
    public int worldX, worldY;
    public int speed;

    // Variabilele in care urmează a fi încărcate sprite-urile entității
    public BufferedImage up1, up2, up3, up4;
    public BufferedImage down1, down2, down3, down4;
    public BufferedImage left1, left2, left3, left4;
    public BufferedImage right1, right2, right3, right4;
    public String direction;

    // spriteCounter tine evidenta numărului de frame-uri care a trecut de când s-a schimbat ultima data sprite-ul
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // solidArea este hit-box-ul entității
    public Rectangle solidArea;

    public boolean collisionOn  = false;
}
