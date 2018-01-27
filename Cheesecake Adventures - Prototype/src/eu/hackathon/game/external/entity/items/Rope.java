package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Rope extends Item {
    public Rope() {
        super(Material.ROPE);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == Material.BRANCH.id()) {
            player.clearInventory();

            ActionLog.request(Material.FISHING_ROD.getDisplayName(), 1500);
            return Material.FISHING_ROD.id();
        }
        ActionLog.request(getMaterial().getDisplayName(), 1500);
        return id();
    }
}
