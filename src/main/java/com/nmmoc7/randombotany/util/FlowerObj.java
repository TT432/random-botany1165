package com.nmmoc7.randombotany.util;

import com.nmmoc7.randombotany.block.FloatingSpecialFlowerBlock;
import com.nmmoc7.randombotany.block.SpecialFlowerBlock;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Lazy;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.function.Supplier;

/**
 * @author DustW
 */
public class FlowerObj<T extends TileEntitySpecialFlower> {
    private static final AbstractBlock.Properties FLOWER_PROPS = AbstractBlock.Properties.from(Blocks.POPPY);
    private static final AbstractBlock.Properties FLOATING_PROPS = ModBlocks.FLOATING_PROPS;
    private static final Item.Properties ITEM_PROPS = ModItems.defaultBuilder();

    String name;
    FlowerAB flowerAB;
    FlowerItemAB flowerItemAB;
    TileEntityType<T> type;

    public FlowerObj(String name, Effect effect, int stewDuration, Supplier<T> tile) {
        this.name = name;
        flowerAB = createBlockDefault(name, effect, stewDuration, tile);
        flowerItemAB = createBlockItemDefault(flowerAB);
        type = createTileDefault(name, tile, flowerAB);
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

    public static <T extends TileEntitySpecialFlower> TileEntityType<T> createTileDefault(String name, Supplier<T> tile, FlowerAB block) {
        TileEntityType<T> result = TileEntityType.Builder.create(tile, block.getA(), block.getB()).build(null);
        ModSpecialFlowers.MOD_FLOWER_TILES.put(name, result);
        return result;
    }

    public static <T extends TileEntitySpecialFlower> FlowerAB createBlockDefault(String name, Effect effect, int stewDuration, Supplier<T> tile) {
        FlowerAB result = new FlowerAB(
                Lazy.of(() -> new SpecialFlowerBlock(effect, stewDuration, FLOWER_PROPS, tile)),
                Lazy.of(() -> new FloatingSpecialFlowerBlock(FLOATING_PROPS, tile))
        );
        ModSpecialFlowers.MOD_FLOWERS.put(name, result);
        return result;
    }

    public static FlowerItemAB createBlockItemDefault(LazyAB<SpecialFlowerBlock, FloatingSpecialFlowerBlock> block) {
        FlowerItemAB result = new FlowerItemAB(
                Lazy.of(() -> new ItemBlockSpecialFlower(block.getA(), ITEM_PROPS)),
                Lazy.of(() -> new ItemBlockSpecialFlower(block.getB(), ITEM_PROPS))
        );
        ModSpecialFlowers.MOD_FLOWER_ITEMS.add(result);
        return result;
    }
}
