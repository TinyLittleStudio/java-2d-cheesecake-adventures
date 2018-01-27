package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Water extends Interactables {
    public Water() {
        super(Material.GROUND_WATER_01);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == Material.FISHING_ROD.id()) {
            player.clearInventory();

            ActionLog.request("Fished Fish", 1500);
            return Material.FISH.id();
        }
        return Material.WATER_BUCKET.id();
    }
}
