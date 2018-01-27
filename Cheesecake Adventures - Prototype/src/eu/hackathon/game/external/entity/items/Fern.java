package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Location;
import eu.hackathon.game.utils.external.Material;

public class Fern extends Item {
    public Fern() {
        super(Material.FERN);
    }

    public Fern(Location location) {
        super(Material.FERN);
    }

    @Override
    public int interact(Player player) {
        ActionLog.request(Material.ROPE.getDisplayName(), 1500);
        return Material.ROPE.id();
    }
}