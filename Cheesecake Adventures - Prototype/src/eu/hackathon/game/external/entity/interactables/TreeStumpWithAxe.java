package eu.hackathon.game.external.entity.interactables;

import eu.hackathon.game.external.entity.Interactables;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class TreeStumpWithAxe extends Interactables {
    public TreeStumpWithAxe() {
        super(Material.TREE_STUMP_02);
    }

    @Override
    public int interact(Player player) {
        if (player.getInventory() == -1 && getMaterial().equals(Material.TREE_STUMP_02)) {
            setMaterial(Material.TREE_STUMP_01);

            ActionLog.request("Axe Equipped", 1500);
            return Material.AXE.id();
        }
        return -1;
    }
}
