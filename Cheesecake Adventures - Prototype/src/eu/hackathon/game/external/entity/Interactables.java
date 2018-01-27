package eu.hackathon.game.external.entity;

import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.external.MaterialBased;

public abstract class Interactables extends Entity implements MaterialBased {

    private Material material;

    public Interactables(Material material) {
        super(EntityType.INTERACTABLE);

        this.material = material;
    }

    public final int id() {
        return material.id();
    }

    public final void setMaterial(Material material) {
        this.material = material;
    }

    public final Material getMaterial() {
        return material;
    }
}
