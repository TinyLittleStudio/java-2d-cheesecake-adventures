package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Ink extends Item {
    public Ink() {
        super(Material.INK);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == Material.FEATHERS.id()) {
            player.clearInventory();

            ActionLog.request(Material.FEATHER_AND_INK.getDisplayName(), 1500);
            return Material.FEATHER_AND_INK.id();
        }
        ActionLog.request(getMaterial().getDisplayName(), 1500);
        return id();
    }
}
