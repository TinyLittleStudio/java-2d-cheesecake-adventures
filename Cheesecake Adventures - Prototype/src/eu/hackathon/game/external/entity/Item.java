package eu.hackathon.game.external.entity;

import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.external.MaterialBased;

public abstract class Item extends Entity implements MaterialBased {
    private Material material;

    public Item(Material material) {
        super(EntityType.ITEM);

        this.material = material;
    }

    public final int id() {
        return material.id();
    }

    public final void setMaterial(Material material) {
        if (isTransmutable()) {
            this.material = material;
        }
    }

    public final Material getMaterial() {
        return material;
    }

    public boolean isTransmutable() {
        return false;
    }
}
