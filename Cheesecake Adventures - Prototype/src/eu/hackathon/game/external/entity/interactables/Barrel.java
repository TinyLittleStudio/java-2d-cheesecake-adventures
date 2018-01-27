package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Barrel extends Interactables {
    public Barrel() {
        super(Material.BARREL_EMPTY);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == -1 && getMaterial().equals(Material.BARREL_FILLED)) {
            setMaterial(Material.BARREL_EMPTY);

            ActionLog.request("Emptied Barrel", 1500);
            return Material.WATER_BUCKET.id();
        }

        if (player.getInventory() == Material.WATER_BUCKET.id() && getMaterial().equals(Material.BARREL_EMPTY)) {
            setMaterial(Material.BARREL_FILLED);

            player.clearInventory();

            ActionLog.request("Filled Barrel", 1500);
            return -1;

        }

        if (player.getInventory() == Material.AXE.id()) {
            setMaterial(Material.GROUND_GRASS_01);

            ActionLog.request("Destroyed Barrel", 1500);
            return -1;

        }
        return -1;
    }
}
