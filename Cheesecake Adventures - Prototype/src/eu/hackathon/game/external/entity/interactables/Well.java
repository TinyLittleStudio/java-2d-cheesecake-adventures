package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Well extends Interactables {
    public Well() {
        super(Material.WELL_EMPTY);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == -1 && !getMaterial().equals(Material.WELL_EMPTY)) {
            setMaterial(Material.WELL_EMPTY);

            ActionLog.request("Emptied Well", 1500);
            return Material.WATER_BUCKET.id();
        }

        if (player.getInventory() != -1 && !getMaterial().equals(Material.WELL_FILLED)) {
            setMaterial(Material.WELL_FILLED);

            player.clearInventory();

            ActionLog.request("Filled Well", 1500);
            return -1;
        }
        return -1;
    }
}
