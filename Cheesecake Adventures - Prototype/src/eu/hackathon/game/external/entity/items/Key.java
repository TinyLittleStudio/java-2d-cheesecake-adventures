package eu.hackathon.game.external.entity.items;

import eu.hackathon.game.GameHandler;
import eu.hackathon.game.external.entity.Item;
import eu.hackathon.game.external.entity.Player;
import eu.hackathon.game.utils.external.ActionLog;
import eu.hackathon.game.utils.external.Material;

public class Key extends Item {
    public Key() {
        super(Material.KEY);
    }

    @Override
    public int interact(Player player) {
        GameHandler.instance().next();
        ActionLog.request(getMaterial().getDisplayName(), 1500);
        return id();
    }
}