package Game;

import Entity.Entity;


public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel p_gamePanel) {
        gamePanel = p_gamePanel;
    }
    public void checkTile(Entity entity) {

        int entityLeftX = entity.worldX + entity.solidArea.x;
        int entityRightX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.worldY + entity.solidArea.y;
        int entityBotY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        // Se salvează poziția hit-box-ului caracterului in variabilele următoare


            int entityLeftCol = entityLeftX / 32;
            int entityRightCol = entityRightX / 32;
            int entityTopRow = entityTopY / 32;
            int entityBotRow = entityBotY / 32;

            // Variabilele tileNum1 si tileNum2 urmează să salveze tile-urile pe care se vor afla
            // colturile hit boxului entității in urma deplasării
            int tileNum1 = 1, tileNum2 = 1;

            // Pentru a se anticipa următoarea poziție a player-ului, depinzând de orientarea sa
            // trebuie fie scăzuta ori adunata viteza cu care acesta se deplasează
            switch (entity.direction) {
                case "sus" -> {
                    entityTopRow = (entityTopY - entity.speed) / 32;
                    tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                }
                case "jos" -> {
                    entityBotRow = (entityBotY + entity.speed) / 32;
                    tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityBotRow];
                    tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBotRow];
                }
                case "stg" -> {
                    entityLeftCol = (entityLeftX - entity.speed) / 32;
                    tileNum1 = gamePanel.tileManager.mapTile[entityLeftCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTile[entityLeftCol][entityBotRow];
                }
                case "drt" -> {
                    entityRightCol = (entityRightX + entity.speed) / 32;
                    tileNum1 = gamePanel.tileManager.mapTile[entityRightCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTile[entityRightCol][entityBotRow];
                }
            }
            // In caz ca vreunul dintre tileNum1 si tileNum2 este un tile cu coliziune, caracterul este
            // împins înapoi
            if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
                switch (entity.direction) {
                    case "sus" -> entity.worldY += 1;
                    case "drt" -> entity.worldX -= 1;
                    case "stg" -> entity.worldX += 1;
                    case "jos" -> entity.worldY -= 1;
                }
            }
        }
    }

