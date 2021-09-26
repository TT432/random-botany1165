package com.nmmoc7.randombotany.event;

import com.nmmoc7.randombotany.RandomBotany;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.util.FlowerAB;
import com.nmmoc7.randombotany.util.FlowerItemAB;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterHandler {
    @SubscribeEvent
    public static void onTileEntityTypeRegister(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                ModSpecialFlowers.MOD_FLOWER_TILES.entrySet().stream().map(tile ->
                        tile.getValue().setRegistryName(new ResourceLocation(RandomBotany.MOD_ID, tile.getKey() + "_tile"))
                ).toArray(TileEntityType[]::new)
        );
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        ArrayList<Block[]> result = new ArrayList<>();
        ModSpecialFlowers.MOD_FLOWERS.forEach((key, value) -> result.add(getFlowerBlocks(value, key)));

        event.getRegistry().registerAll(
            merger(
                result.toArray(new Block[0][0])
            )
        );
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        ArrayList<Item[]> result = new ArrayList<>();
        ModSpecialFlowers.MOD_FLOWER_ITEMS.forEach(item -> result.add(getFlowerItems(item)));

        event.getRegistry().registerAll(
            merger(
                result.toArray(new Item[0][0])
            )
        );
    }

    public static Item[] merger(Item[]... items) {
        ArrayList<Item> result = new ArrayList<>();

        for (Item[] item : items) {
            result.add(item[0]);
            result.add(item[1]);
        }

        return result.toArray(new Item[0]);
    }

    public static Block[] merger(Block[]... blocks) {
        ArrayList<Block> result = new ArrayList<>();

        for (Block[] block : blocks) {
            result.add(block[0]);
            result.add(block[1]);
        }

        return result.toArray(new Block[0]);
    }

    public static Item[] getFlowerItems(FlowerItemAB item) {
        return new Item[] {
                item.getA().setRegistryName(item.getA().getBlock().getRegistryName()),
                item.getB().setRegistryName(item.getB().getBlock().getRegistryName())
        };
    }

    public static Block[] getFlowerBlocks(FlowerAB block, String name) {
        return new Block[] {
                block.getA().setRegistryName(new ResourceLocation(RandomBotany.MOD_ID, name)),
                block.getB().setRegistryName(new ResourceLocation(RandomBotany.MOD_ID, name + "_floating"))
        };
    }
}
