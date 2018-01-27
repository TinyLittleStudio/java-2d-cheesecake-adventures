package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Chest extends Interactables {
    private boolean isEmpty = false;

    public Chest() {
        super(Material.CHEST_CLOSED);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == -1) {
            ActionLog.request(getMaterial().equals(Material.CHEST_OPENED) ? "Closed Chest" : "Opened Chest", 1500);

            setMaterial(getMaterial().equals(Material.CHEST_OPENED) ? Material.CHEST_CLOSED : Material.CHEST_OPENED);

            if (!isEmpty) {
                isEmpty = true;

                ActionLog.request("Found " + Material.INK.getDisplayName(), 1500);
                return Material.INK.id();
            } else {
                ActionLog.request("Chest is Empty! " + Material.INK.getDisplayName(), 1500);
            }
        }
        return -1;
    }
}
