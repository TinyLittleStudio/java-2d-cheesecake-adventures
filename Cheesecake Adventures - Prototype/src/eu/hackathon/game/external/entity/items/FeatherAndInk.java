package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class FeatherAndInk extends Item {
    public FeatherAndInk() {
        super(Material.FEATHER_AND_INK);
    }

    @Override
    public int interact(Player player) {
        ActionLog.request(getMaterial().getDisplayName(), 1500);
        return id();
    }
}