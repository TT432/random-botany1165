package com.nmmoc7.randombotany.stat;

import com.nmmoc7.randombotany.stat.type.StatTypesManager;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author DustW
 **/
@Mod.EventBusSubscriber
public class FlowerCraftListenerStatHolder implements IStatHolder<Item> {
    private static final String ITEM_TAG_APOTHECARY_SPAWNED = "ApothecarySpawned";

    private final Item item;

    public FlowerCraftListenerStatHolder(Item item) {
        this.item = item;
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public StatType<Item> getStatType() {
        return StatTypesManager.MAKE_FLOWER.get();
    }

    @SubscribeEvent
    public static void pickup(PlayerEvent.ItemPickupEvent event) {
        ItemEntity entity = event.getOriginalEntity();

        if (entity.world.isRemote || !entity.getTags().contains(ITEM_TAG_APOTHECARY_SPAWNED)) {
            return;
        }

        IStatHolder<Item> stat = StatManager.WITCH_CRAFT;

        if (event.getStack().getItem() == stat.getItem()) {
            stat.add(event.getPlayer());
            System.out.println(stat.get((ServerPlayerEntity) event.getPlayer()));
        }
    }
}
