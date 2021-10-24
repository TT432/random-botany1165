package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.functional.BaseFunctionalFlower;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityType;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DustW
 */
public class FarmerAssistantFlower extends BaseGeneratingFlower {
    public FarmerAssistantFlower() {
        super(ModSpecialFlowers.FARMER_ASSISTANT.getType());
    }

    private static Map<Item, Integer> SEEDS = null;

    private static Map<Item, Integer> getSeeds() {
        if (SEEDS == null) {
            SEEDS = new HashMap<>(6);
            SEEDS.put(Items.WHEAT_SEEDS, 50);
            SEEDS.put(Items.MELON_SEEDS, 70);
            SEEDS.put(Items.POISONOUS_POTATO, 280);
            SEEDS.put(Items.POTATO, 70);
            SEEDS.put(Items.BEETROOT_SEEDS, 70);
            SEEDS.put(Items.PUMPKIN_SEEDS, 70);
        }

        return SEEDS;
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world.isRemote) {
            return;
        }

        List<ItemEntity> items = searchItems(itemEntity ->
                getSeeds().containsKey(itemEntity.getItem().getItem()) && itemEntity.getAge() > getSlowdownFactor());

        items.forEach(item -> {
            addMana(getSeeds().get(item.getItem().getItem()) * item.getItem().getCount());
            item.remove();
        });
    }

    @Override
    public int getSlowdownFactor() {
        return 5;
    }

    @Override
    int getRange() {
        return 9;
    }
}
