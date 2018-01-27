package eu.hackathon.game.external.entity;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.controls.KeyCode;
import eu.hackathon.cheesecake.internal.controls.keyboard.Keyboard;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.cheesecake.internal.graphics.Image;
import eu.hackathon.cheesecake.utils.external.SceneEvent;
import eu.hackathon.cheesecake.utils.external.SceneFadeType;
import eu.hackathon.cheesecake.utils.internal.Time;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.Level;
import eu.hackathon.game.internal.MenuPanel;
import eu.hackathon.game.utils.Resources;
import eu.hackathon.game.utils.external.Location;
import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.external.MaterialBased;
import eu.hackathon.game.utils.external.PlayerDirection;
import eu.hackathon.game.utils.internal.Animation;

public class Player extends Entity implements Structure {
    public static final int CAMERA_X = Math.round(((GameHandler.instance().getWindow().getWidth() - Entity.SQR_SIZE) / 2) / Entity.SQR_SIZE) * Entity.SQR_SIZE;
    public static final int CAMERA_Y = Math.round(((GameHandler.instance().getWindow().getHeight() - Entity.SQR_SIZE) / 2) / Entity.SQR_SIZE) * Entity.SQR_SIZE;

    private Location location;
    private int inventory = -1;

    private PlayerDirection previous, next = PlayerDirection.DOWN;

    private Animation animation;

    private boolean isMoving = false, isWalking = false;
    private double hasMoved = 0.0d;
    private double keyInvokedTimeWaited = 0.0d;

    private Image iconTexture;
    private double time = 0.0d, lastTime = System.currentTimeMillis();
    private boolean isIconVisible = false;

    private Level level;
    private boolean isExit = false;

    public Player(Location location, Level level) {
        super(EntityType.PLAYER);

        this.location = location;

        animation = new Animation(Player.CAMERA_X, Player.CAMERA_Y, next.getImages());
        animation.setVelocity(100.0f);

        this.level = level;
    }

    @Override
    public int interact(Player player) {
        return -1;
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        if (!GameHandler.instance().getScreen().isFinished() || level == null) {
            return;
        }

        if (isMoving) {
            animation.tick();
        }

        if (isIconVisible) {
            time += System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();

            if (time > 1500) {
                isIconVisible = false;

                if (isExit) {
                    isExit = false;
                }
                time -= 1500;
            }
        }

        if (!isMoving) {
            if (Keyboard.getKeyUp(KeyCode.Q)) {
                if (inventory != -1) {
                    int index = (location.getX() / Entity.SQR_SIZE) + next.getX() + ((location.getY() / Entity.SQR_SIZE) + next.getY()) * this.level.getWidth();

                    Entity entity = this.level.getEntity(index);

                    if (entity == null) {
                        Material material = Material.find(inventory);

                        if (material != null && material.isInstantiable()) {
                            Material location = Material.find(this.level.getWorldData()[index]);

                            if (location != null && location.isWalkable()) {
                                if(!material.equals(Material.WATER_BUCKET))this.level.getEntities()[index] = material.instantiate();
                                clearInventory();
                            }
                        }
                    }
                }
                action(Resources.ICON_KEY_Q);

                Resources.CLICK_02.play();
            }

            if (Keyboard.getKeyUp(KeyCode.E)) {
                int index = (location.getX() / Entity.SQR_SIZE) + next.getX() + ((location.getY() / Entity.SQR_SIZE) + next.getY()) * level.getWidth();

                Entity entity = this.level.getEntity(index);

                if (entity != null) {
                    int materialId = entity.interact(this);

                    if (materialId != -1 && inventory == -1) {
                        if (entity instanceof Item) {
                            this.level.getEntities()[index] = null;
                        }
                        if(materialId!=Material.CHEESECAKE.id())this.inventory = materialId;
                    }
                }
                action(Resources.ICON_KEY_E);

                Resources.CLICK_02.play();
            }
        }

        if (Keyboard.getKeyUp(KeyCode.ESCAPE)) {
            if (!isExit) {
                isExit = true;
            } else {
                isExit = false;
                isIconVisible = false;
                iconTexture = null;

                GameHandler.instance().getScreen().request(new SceneEvent(SceneFadeType.OUT) {
                    @Override
                    public void execute() {
                        GameHandler.instance().getPanelHandler().setPanel(MenuPanel.KEY);
                        GameHandler.instance().getScreen().request(new SceneEvent(SceneFadeType.IN) {
                            @Override
                            public void execute() { /* ... */ }
                        });
                    }
                });
            }
            action(Resources.ICON_KEY_ESC);

            Resources.CLICK_02.play();
        }

        if (Keyboard.getKeyDown(KeyCode.W) || Keyboard.getKeyDown(KeyCode.A) || Keyboard.getKeyDown(KeyCode.S) || Keyboard.getKeyDown(KeyCode.D)) {
            if (!isWalking) {
                animation.update();
                isWalking = true;
            }
        }

        if (Keyboard.getKeyUp(KeyCode.W) || Keyboard.getKeyUp(KeyCode.A) || Keyboard.getKeyUp(KeyCode.S) || Keyboard.getKeyUp(KeyCode.D)) {
            if (isWalking) {
                animation.update();
                isWalking = false;
            }
        }

        if (!isMoving) {
            if (Keyboard.getKey(KeyCode.W)) {
                move(PlayerDirection.UP);
            }

            if (Keyboard.getKey(KeyCode.A)) {
                move(PlayerDirection.LEFT);
            }

            if (Keyboard.getKey(KeyCode.S)) {
                move(PlayerDirection.DOWN);
            }

            if (Keyboard.getKey(KeyCode.D)) {
                move(PlayerDirection.RIGHT);
            }
        }

        if (previous != null && next != null && !previous.equals(next)) {
            animation.setImages(next.getImages());
        }
        previous = next;

        if (isMoving) {
            double m = Time.deltaTime() * 118;

            if (m + hasMoved > Entity.SQR_SIZE) {
                m = Entity.SQR_SIZE - hasMoved;
            }
            location.setX(location.getRawX() + next.getX() * m);
            location.setY(location.getRawY() + next.getY() * m);
            hasMoved += m;

            if (hasMoved >= Entity.SQR_SIZE) {
                isMoving = false;
                hasMoved = 0;

                animation.setIndex(0);
            }
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        animation.draw(extendedGraphics);

        if (isIconVisible) {
            if (iconTexture != null) {
                extendedGraphics.drawImage(iconTexture, Player.CAMERA_X, Player.CAMERA_Y - iconTexture.getHeight());
            }
        }
    }

    @Override
    public void dispose() { /* ... */}

    private void move(PlayerDirection playerDirection) {
        if (!isMoving) {
            if (next.equals(playerDirection)) {
                if (isWalkable(playerDirection) && (System.currentTimeMillis() - keyInvokedTimeWaited >= 100)) {
                    isMoving = true;
                }
            } else {
                next = playerDirection;
                keyInvokedTimeWaited = System.currentTimeMillis();
            }
        }
    }

    private boolean isWalkable(PlayerDirection playerDirection) {
        int x = (location.getX() / Entity.SQR_SIZE) + playerDirection.getX();
        int y = (location.getY() / Entity.SQR_SIZE) + playerDirection.getY();
        int i = x + y * level.getWidth();

        if (x >= level.getWidth() || x < 0 || y >= level.getHeight() || y < 0) {
            return false;
        }

        Entity entity = level.getEntities()[i];

        if (entity != null && entity instanceof MaterialBased) {
            MaterialBased materialBased = ((MaterialBased) entity);

            if (materialBased.getMaterial() != null) {
                return materialBased.getMaterial().isWalkable();
            }
        }

        Material material = Material.find(level.getWorldData()[i]);

        return material != null && material.isWalkable();
    }

    private void action(Image image) {
        iconTexture = image;
        isIconVisible = true;
        time = 0.0d;
        lastTime = System.currentTimeMillis();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setInventory(int id) {
        this.inventory = id;
    }

    public int getInventory() {
        return inventory;
    }

    public void clearInventory() {
        this.inventory = -1;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }
}
