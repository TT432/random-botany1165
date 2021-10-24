package com.nmmoc7.randombotany.specialflower;

import com.nmmoc7.randombotany.block.FloatingSpecialFlowerBlock;
import com.nmmoc7.randombotany.block.SpecialFlowerBlock;
import com.nmmoc7.randombotany.specialflower.generating.FarmerAssistantFlower;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBelieverFlower;
import com.nmmoc7.randombotany.specialflower.generating.WitchFlower;
import com.nmmoc7.randombotany.util.FlowerAB;
import com.nmmoc7.randombotany.util.FlowerItemAB;
import com.nmmoc7.randombotany.util.FlowerObj;
import com.nmmoc7.randombotany.util.LazyAB;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.util.Lazy;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModSpecialFlowers {
    private static final AbstractBlock.Properties FLOWER_PROPS = AbstractBlock.Properties.from(Blocks.POPPY);
    private static final AbstractBlock.Properties FLOATING_PROPS = ModBlocks.FLOATING_PROPS;
    private static final Item.Properties ITEM_PROPS = ModItems.defaultBuilder();

    public static final Map<String, FlowerAB> MOD_FLOWERS = new HashMap<>();
    public static final ArrayList<FlowerItemAB> MOD_FLOWER_ITEMS = new ArrayList<>();
    public static final Map<String, TileEntityType<? extends TileEntitySpecialFlower>> MOD_FLOWER_TILES = new HashMap<>();

    public static final FlowerObj<TinyPotatoBelieverFlower> TINY_POTATO_BELIEVER =
            new FlowerObj<>("believer", Effects.HUNGER, 180, TinyPotatoBelieverFlower::new);

    public static final FlowerObj<WitchFlower> WITCH =
            new FlowerObj<>("witch", Effects.POISON, 180, WitchFlower::new);

    public static final FlowerObj<FarmerAssistantFlower> FARMER_ASSISTANT =
            new FlowerObj<>("farmer_assistant", Effects.LUCK, 180, FarmerAssistantFlower::new);

    public static <T extends TileEntitySpecialFlower> TileEntityType<T> createTileDefault(String name, Supplier<T> tile, FlowerAB block) {
        TileEntityType<T> result = TileEntityType.Builder.create(tile, block.getA(), block.getB()).build(null);
        MOD_FLOWER_TILES.put(name, result);
        return result;
    }

    public static <T extends TileEntitySpecialFlower> FlowerAB createBlockDefault(String name, Effect effect, int stewDuration, Supplier<T> tile) {
        FlowerAB result = new FlowerAB(
                Lazy.of(() -> new SpecialFlowerBlock(effect, stewDuration, FLOWER_PROPS, tile)),
                Lazy.of(() -> new FloatingSpecialFlowerBlock(FLOATING_PROPS, tile))
        );
        MOD_FLOWERS.put(name, result);
        return result;
    }

    public static FlowerItemAB createBlockItemDefault(LazyAB<SpecialFlowerBlock, FloatingSpecialFlowerBlock> block) {
        FlowerItemAB result = new FlowerItemAB(
                Lazy.of(() -> new ItemBlockSpecialFlower(block.getA(), ITEM_PROPS)),
                Lazy.of(() -> new ItemBlockSpecialFlower(block.getB(), ITEM_PROPS))
        );
        MOD_FLOWER_ITEMS.add(result);
        return result;
    }
}
