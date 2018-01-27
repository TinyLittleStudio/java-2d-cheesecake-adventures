package eu.hackathon.game.external;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.external.ui.GUI;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.entity.Entity;
import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.Resources;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Location;
import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.external.MaterialBased;

public class Level implements Structure {
    public static final int BACKGROUND = 0xff000000;

    private String name;
    private int width, height;

    private int[] data;
    private Entity[] entities;
    private int key;

    private Player player;

    private double lastTime = System.currentTimeMillis();
    private double time = 0.0d;
    private int limit = 8, offset = 0;
    private boolean toggle = false;

    public Level(LevelData levelData) {
        this.name = levelData.name;
        this.width = levelData.width;
        this.height = levelData.height;

        this.key = levelData.key;

        this.data = levelData.data;

        this.player = new Player(new Location(levelData.x * Entity.SQR_SIZE, levelData.y * Entity.SQR_SIZE), this);

        this.entities = new Entity[width * height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Material material = Material.find(levelData.entities[x + y * width]);

                if (material != null) {
                    this.entities[x + y * width] = material.instantiate();
                }
            }
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (time > 80) {
            offset += (toggle ? 1 : -1);

            if (offset >= limit || offset <= -limit) {
                toggle = !toggle;
            }
            time -= 80;
        }
        player.tick();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity entity = entities[x + width * y];

                if (entity != null) {
                    entity.tick();
                }
            }
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        extendedGraphics.setBackgroundColor(Level.BACKGROUND);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Material material = Material.find(data[x + width * y]);

                if (material != null) {
                    extendedGraphics.drawImage(material.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }
            }
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Material material = null;

                Entity entity = entities[x + width * y];

                if (entity != null) {
                    if (entity instanceof MaterialBased) {
                        MaterialBased materialBased = (MaterialBased) entity;
                        material = materialBased.getMaterial();
                    }

                    if (material != null) {
                        extendedGraphics.drawImage(material.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), ((entity instanceof Item) ? this.offset : 0) + Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                    }
                    entity.draw(extendedGraphics);
                }
            }
        }
        player.draw(extendedGraphics);

        for (int x = -1; x < width + 1; x++) {
            for (int y = -1; y < height + 1; y++) {
                if (x == -1 && y == -1) {
                    extendedGraphics.drawImage(Material.BORDER_01.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x != -1 && x != width && y == -1) {
                    extendedGraphics.drawImage(Material.BORDER_03.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == width && y == -1) {
                    extendedGraphics.drawImage(Material.BORDER_05.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x != -1 && x != width && y == 0) {
                    extendedGraphics.drawImage(Material.BORDER_08.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == -1 && y != -1 && y != height) {
                    extendedGraphics.drawImage(Material.BORDER_11.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == 0 && y != -1 && y != height) {
                    extendedGraphics.drawImage(Material.BORDER_12.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == width - 1 && y != -1 && y != height) {
                    extendedGraphics.drawImage(Material.BORDER_13.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == width && y != -1 && y != height) {
                    extendedGraphics.drawImage(Material.BORDER_14.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x != -1 && x != width && y == height - 1) {
                    extendedGraphics.drawImage(Material.BORDER_17.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == -1 && y == height) {
                    extendedGraphics.drawImage(Material.BORDER_20.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x != -1 && x != width && y == height) {
                    extendedGraphics.drawImage(Material.BORDER_22.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }

                if (x == width && y == height) {
                    extendedGraphics.drawImage(Material.BORDER_24.getTexture(), Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                }
            }
        }

        if (GUI.drawButton(extendedGraphics, Resources.REFRESH, 12, GameHandler.instance().getWindow().getHeight() - Resources.REFRESH.getHeight() * 3, Resources.REFRESH.getWidth(), Resources.REFRESH.getHeight())) {
            Resources.CLICK_01.play();
            GameHandler.instance().reload();
        }

        if (GUI.drawButton(extendedGraphics, Resources.INVENTORY_SLOT, 12, GameHandler.instance().getWindow().getHeight() - Resources.INVENTORY_SLOT.getHeight() * 2, Resources.INVENTORY_SLOT.getWidth(), Resources.INVENTORY_SLOT.getHeight())) {
            Resources.CLICK_01.play();
        }

        if (player.getInventory() != -1) {
            Material material = Material.find(player.getInventory());

            if (material != null) {
                extendedGraphics.drawImage(material.getTexture(), 12, GameHandler.instance().getWindow().getHeight() - material.getTexture().getHeight() * 2);
            }
        }
    }

    public final void load() {
        for (Entity entity : entities) {
            if (entity != null) {
                entity.init();
            }
        }
        lastTime = System.currentTimeMillis();
        time = 0.0d;

        ActionLog.request(name, 2000);
    }

    public final void unload() {

    }

    @Override
    public void dispose() { /* ... */ }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public final Entity getEntity(int index) {
        if (index > -1 && index < entities.length) {
            return entities[index];
        }
        return null;
    }

    public final Entity[] getEntities() {
        return entities;
    }

    public final int[] getWorldData() {
        return data;
    }

    public final int key() {
        return key;
    }

    public final Player getPlayer() {
        return player;
    }

    public final int getKey() {
        return key;
    }

    public Location getLocationOf(Entity entity) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Entity e = entities[x + width * y];

                if (e != null) {
                    if (entity.equals(e)) {
                        return new Location(Player.CAMERA_X + x * Entity.SQR_SIZE - player.getLocation().getX(), Player.CAMERA_Y + y * Entity.SQR_SIZE - player.getLocation().getY());
                    }
                }
            }
        }
        return null;
    }
}
