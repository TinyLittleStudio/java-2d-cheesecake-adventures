package eu.hackathon.game.external.entity;

import eu.hackathon.cheesecake.Structure;
import eu.hackathon.cheesecake.internal.graphics.ExtendedGraphics;

public abstract class Entity implements Structure {
    public static final int SQR_SIZE = 64;

    private final EntityType entityType;

    public Entity(final EntityType entityType) {
        this.entityType = entityType;
    }

    public final EntityType getEntityType() {
        return entityType;
    }

    @Override
    public void init() { /* ... */ }

    @Override
    public void tick() { /* ... */ }

    @Override
    public void draw(ExtendedGraphics extendedGraphics) { /* ... */ }

    @Override
    public void dispose() { /* ... */ }

    public abstract int interact(Player player);
}
