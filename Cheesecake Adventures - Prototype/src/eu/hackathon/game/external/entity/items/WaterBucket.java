package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class WaterBucket extends Item {
    public WaterBucket() {
        super(Material.WATER_BUCKET);
    }

    @Override
    public int interact(Player player) {
        ActionLog.request(getMaterial().getDisplayName(), 1500);
        return id();
    }
}
