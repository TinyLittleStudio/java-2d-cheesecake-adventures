package eu.hackathon.game.utils.external;

import eu.hackathon.cheesecake.internal.graphics.Image;
import eu.hackathon.game.external.entity.Entity;
import eu.hackathon.game.external.entity.interactables.*;
import eu.hackathon.game.external.entity.items.*;
import eu.hackathon.game.external.entity.obstacles.*;
import eu.hackathon.game.utils.Resources;

public enum Material {
    GROUND_GRASS_01(0, "Grass", Resources.TILESET[54], null, false, false, false),
    GROUND_GRASS_02(1, "Grass", Resources.TILESET[64], null, false, false, false),
    GROUND_GRASS_03(2, "Grass", Resources.TILESET[74], null, false, false, false),

    BORDER_01(50, "", Resources.TILESET[55], null, true, false, false),
    BORDER_02(51, "", Resources.TILESET[56], null, true, false, false),
    BORDER_03(52, "", Resources.TILESET[57], null, true, false, false),
    BORDER_04(53, "", Resources.TILESET[58], null, true, false, false),
    BORDER_05(54, "", Resources.TILESET[59], null, true, false, false),
    BORDER_06(55, "", Resources.TILESET[65], null, true, false, false),
    BORDER_07(56, "", Resources.TILESET[66], null, true, false, false),
    BORDER_08(57, "", Resources.TILESET[67], null, true, false, false),
    BORDER_09(58, "", Resources.TILESET[68], null, true, false, false),
    BORDER_10(59, "", Resources.TILESET[69], null, true, false, false),
    BORDER_11(60, "", Resources.TILESET[75], null, true, false, false),
    BORDER_12(61, "", Resources.TILESET[76], null, true, false, false),
    BORDER_13(62, "", Resources.TILESET[78], null, true, false, false),
    BORDER_14(63, "", Resources.TILESET[79], null, true, false, false),
    BORDER_15(64, "", Resources.TILESET[85], null, true, false, false),
    BORDER_16(55, "", Resources.TILESET[86], null, true, false, false),
    BORDER_17(56, "", Resources.TILESET[87], null, true, false, false),
    BORDER_18(57, "", Resources.TILESET[88], null, true, false, false),
    BORDER_19(58, "", Resources.TILESET[89], null, true, false, false),
    BORDER_20(59, "", Resources.TILESET[95], null, true, false, false),
    BORDER_21(60, "", Resources.TILESET[96], null, true, false, false),
    BORDER_22(61, "", Resources.TILESET[97], null, true, false, false),
    BORDER_23(62, "", Resources.TILESET[98], null, true, false, false),
    BORDER_24(63, "", Resources.TILESET[99], null, true, false, false),

    TREE_STUMP_01(20, "Tree Stump", Resources.TILESET[24], TreeStump.class, true, false, false),
    TREE_STUMP_02(21, "Tree Stump with Axe", Resources.TILESET[25], TreeStumpWithAxe.class, true, false, false),

    HEDGE(22, "Hedge", Resources.TILESET[26], Hedge.class, true, false, false),

    STONE_WALL_01(23, "Stone Wall", Resources.TILESET[27], StoneWall.class, true, false, false),
    STONE_WALL_02(24, "Stone Wall with Crack", Resources.TILESET[28], StoneWallCracked.class, true, false, false),

    FLOWERS_01(25, "Flowers", Resources.TILESET[84], FlowerMultiple.class, true, false, false),
    FLOWERS_03(27, "Flowers", Resources.TILESET[94], FlowerSingle.class, true, false, false),

    FERN(1001, "Fern", Resources.TILESET[19], Fern.class, true, false, true),
    BRANCH(1002, "Branch", Resources.TILESET[18], Branch.class, true, false, true),
    ROPE(1003, "Rope", Resources.TILESET[16], Rope.class, true, false, true),
    WATER_BUCKET(1004, "Water Bucket", Resources.TILESET[35], WaterBucket.class, true, true, true),
    FISHING_ROD(1005, "Fishing Rod", Resources.TILESET[13], FishingRod.class, true, false, true),
    FISH(1006, "Fish", Resources.TILESET[15], Fish.class, true, false, true),
    COOKED_FISH(1007, "Cooked Fish", Resources.TILESET[14], CookedFish.class, true, false, true),
    FEATHERS(1008, "Feathers", Resources.TILESET[5], Feather.class, true, false, true),
    FEATHER_AND_INK(1009, "Feather and Ink", Resources.TILESET[4], FeatherAndInk.class, true, false, true),
    CHEESECAKE(1010, "Cheesecake", Resources.TILESET[23], Cheesecake.class, true, false, true),
    IRON_ORE(1011, "Iron IronOre", Resources.TILESET[0], IronOre.class, true, false, true),
    AXE(1012, "Axe", Resources.TILESET[3], Axe.class, true, false, true),
    INK(10013, "Ink", Resources.TILESET[6], Ink.class, true, false, true),

    BARREL_EMPTY(2001, "Barrel", Resources.TILESET[34], Barrel.class, true, false, true),
    BARREL_FILLED(2002, "Barrel", Resources.TILESET[35], Barrel.class, true, false, true),
    WELL_EMPTY(2003, "Well", Resources.TILESET[29], Well.class, true, false, true),
    WELL_FILLED(2004, "Well", Resources.TILESET[39], Well.class, true, false, true),
    FURNACE(2005, "Furnace", Resources.TILESET[36], Furnace.class, true, false, true),
    FURNACE_BURNING(2006, "Furnace", Resources.TILESET[37], Furnace.class, true, false, true),
    CHEST_CLOSED(2007, "Chest", Resources.TILESET[44], Chest.class, true, false, true),
    CHEST_OPENED(2010, "Chest", Resources.TILESET[47], Chest.class, true, false, true),
    GROUND_WATER_01(3, "Water", Resources.TILESET[53], Water.class, false, true, false),
    GROUND_WATER_02(4, "Water", Resources.TILESET[63], Water.class, false, true, false),
    KEY(2011, "Key", Resources.TILESET[17], Key.class, true, false, true),
    DOVE(2012, "Dove (Key)", Resources.TILESET[17], Key.class, true, false, true),
    PERSON(2013, "Person", Resources.TILESET[1], Person.class, true, false, true);

    private final int id;
    private final String name;
    private final boolean isSolid, isLiquid, isInstantiable;

    private final Class obj;

    private final Image image;

    Material(final int id, final String name, final Image image, final Class obj, final boolean isSolid, final boolean isLiquid, final boolean isInstantiable) {
        this.id = id;
        this.name = name;
        this.isLiquid = isLiquid;
        this.isSolid = isSolid;

        this.isInstantiable = isInstantiable;

        this.obj = obj;

        this.image = image;
    }

    public final int id() {
        return id;
    }

    public final String getDisplayName() {
        return name;
    }

    public final boolean isSolid() {
        return isSolid;
    }

    public final boolean isLiquid() {
        return isLiquid;
    }

    public final boolean isWalkable() {
        return !isSolid && !isLiquid;
    }

    public final Image getTexture() {
        return image;
    }

    public final Entity instantiate() {
        try {
            if (obj != null) {
                return Entity.class.cast(obj.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public final boolean isInstantiable() {
        return isInstantiable;
    }

    public static Material find(int id) {
        for (Material material : Material.values()) {
            if (id == material.id) {
                return material;
            }
        }
        return null;
    }
}
