package Entity;

import Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Mob extends Entity {
    GamePanel gamePanel;
    Player player;
    int spawnX;
    int spawnY;
    public Mob(GamePanel p_gamePanel, Player p_player, int p_spawnX, int p_spawnY, int p_speed) {
        gamePanel = p_gamePanel;
        player = p_player;
        getMobImage();
        solidArea = new Rectangle(28, 28, 10, 38);
        spawnX = p_spawnX;
        spawnY = p_spawnY;
        setSpawnPosition(p_spawnX, p_spawnY, p_speed);

    }

    public void setSpawnPosition(int X, int Y, int p_speed) {
        worldX = X;
        worldY = Y;
        speed = p_speed;
        collisionOn = false;
        direction = "jos";
    }

    public void update() {
        gamePanel.collisionChecker.checkTile(this);
        if(!collisionOn) {
            if(worldX >= player.worldX - 5 && worldX <= player.worldX + 5 && worldY >= player.worldY - 5 && worldY <= player.worldY + 5) {
                player.setDead();
            }
            if (player.worldY < worldY && player.worldX > worldX) {
                worldY -= speed;
                worldX += speed;
                direction = "sus";
            } else if(player.worldY < worldY && player.worldX < worldX) {
                worldY -= speed;
                worldX -= speed;
                direction = "sus";
            } else if(player.worldY > worldY && player.worldX > worldX) {
                worldX += speed;
                worldY += speed;
                direction = "jos";
            } else if(player.worldY > worldY && player.worldX < worldX) {
                worldX -= speed;
                worldY += speed;
                direction = "jos";
            } else if(player.worldY < worldY ) {
                worldY -= speed;
                direction = "sus";
            } else if (player.worldY > worldY) {
                worldY += speed;
                direction = "jos";
            } else if (player.worldX < worldX) {
                worldX -= speed;
                direction = "stg";
            } else if (player.worldX > worldX) {
                worldX += speed;
                direction = "drt";
            }
        }
        collisionOn = false;

        // De fiecare data când se ajunge la 10 frame-uri, sprite-ul se schimba, rezultând într-o animație
        spriteCounter++;
        if(spriteCounter > 10) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 3;
            } else if(spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g) {

        BufferedImage img = null;
        switch (direction) {
            case "sus":
                if (spriteNum == 1) {
                    img = up1;
                } else if (spriteNum == 2) {
                    img = up2;
                } else if (spriteNum == 3) {
                    img = up3;
                } else {
                    img = up4;
                }
                break;
            case "jos":
                if (spriteNum == 1) {
                    img = down1;
                } else if (spriteNum == 2) {
                    img = down2;
                } else if (spriteNum == 3) {
                    img = down3;
                } else {
                    img = down4;
                }
                break;
            case "stg":
                if (spriteNum == 1) {
                    img = left1;
                } else if (spriteNum == 2) {
                    img = left2;
                } else if (spriteNum == 3) {
                    img = left3;
                } else {
                    img = left4;
                }
                break;
            case "drt":
                if (spriteNum == 1) {
                    img = right1;
                } else if (spriteNum == 2) {
                    img = right2;
                } else if (spriteNum == 3) {
                    img = right3;
                } else {
                    img = right4;
                }
                break;

        }
        g.drawImage(img, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);

    }

    public void getMobImage()  {
        try {
            // Se încarcă sprite-urile pentru fiecare orientare a caracterului
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Spate_0.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Spate_1.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Spate_2.png")));
            up4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Spate_3.png")));

            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Fata_0.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Fata_1.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Fata_2.png")));
            down4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Fata_3.png")));

            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Stg_0.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Stg_1.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Stg_2.png")));
            left4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Stg_3.png")));

            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Drt_0.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Drt_1.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Drt_2.png")));
            right4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Player/Minotaur_Mers_Drt_3.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
