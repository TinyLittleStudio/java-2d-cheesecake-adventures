package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;
import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.entity.Entity;
import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.Resources;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Location;
import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.internal.Animation;

public class Furnace extends Interactables {
    private Animation animation = new Animation(-Entity.SQR_SIZE, -Entity.SQR_SIZE, Resources.TILESET[37], Resources.TILESET[38]);

    public Furnace() {
        super(Material.FURNACE);
    }

    @Override
    public void init() {
        if (GameHandler.instance().getLevel() != null) {
            Location location = GameHandler.instance().getLevel().getLocationOf(this);

            animation.setLocation(location.getX(), location.getY());
        }
        animation.update();
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == Material.BRANCH.id() && getMaterial().equals(Material.FURNACE)) {
            player.clearInventory();

            setMaterial(Material.FURNACE_BURNING);

            ActionLog.request("Furnace Ignited", 1500);
            return -1;
        }

        if (player.getInventory() == Material.FISH.id() && getMaterial().equals(Material.FURNACE_BURNING)) {
            setMaterial(Material.FURNACE);
            player.clearInventory();

            ActionLog.request(Material.COOKED_FISH.getDisplayName(), 1500);
            return Material.COOKED_FISH.id();
        }

        if (player.getInventory() == Material.IRON_ORE.id() && getMaterial().equals(Material.FURNACE_BURNING)) {
            setMaterial(Material.FURNACE);

            ActionLog.request(Material.AXE.getDisplayName(), 1500);
            return Material.AXE.id();
        }
        return -1;
    }

    @Override
    public void tick() {
        if (getMaterial().equals(Material.FURNACE_BURNING)) {
            if (GameHandler.instance().getLevel() != null) {
                Location location = GameHandler.instance().getLevel().getLocationOf(this);

                animation.setLocation(location.getX(), location.getY());
            }
            animation.tick();
        }
    }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) {
        if (getMaterial().equals(Material.FURNACE_BURNING)) {
            animation.draw(extendedGraphics);
        }
    }
}

