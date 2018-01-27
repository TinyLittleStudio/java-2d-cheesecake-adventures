package eu.hackathon.game.external.entity;

import eu.hackathon.game.utils.external.Material;
import eu.hackathon.game.utils.external.MaterialBased;

public abstract class Obstacle extends Entity implements MaterialBased {
    private Material material;

    public Obstacle(Material material) {
        super(EntityType.OBSTACLE);

        this.material = material;
    }

    @Override
    public final int interact(Player player) {
        return -1;
    }

    public final Material getMaterial() {
        return material;
    }

    @Override
    public final void setMaterial(Material material) { /* ... */ }
}
