package com.nmmoc7.randombotany.util;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.block.Block;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;

import java.util.function.Supplier;

/**
 * @author DustW
 */
public class FlowerObj<T extends TileEntitySpecialFlower> {
    String name;
    FlowerAB flowerAB;
    FlowerItemAB flowerItemAB;
    TileEntityType<T> type;

    public FlowerObj(String name, Effect effect, int stewDuration, Supplier<T> tile) {
        this.name = name;
        flowerAB = ModSpecialFlowers.createBlockDefault(name, effect, stewDuration, tile);
        flowerItemAB = ModSpecialFlowers.createBlockItemDefault(flowerAB);
        type = ModSpecialFlowers.createTileDefault(name, tile, flowerAB);
    }

    public String getName() {
        return name;
    }

    public FlowerAB getBlock() {
        return flowerAB;
    }

    public FlowerItemAB getItem() {
        return flowerItemAB;
    }

    public TileEntityType<T> getType() {
        return type;
    }
}
