package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import vazkii.botania.common.item.ItemAncientWill;

import java.util.List;

/**
 * @author DustW
 **/
public class Willeater extends BaseGeneratingFlower {
    public Willeater() {
        super(ModSpecialFlowers.WILLEATER.getType());
    }

    Item lastWill;
    int count;

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote) {
            return;
        }

        List<ItemEntity> list = searchItems(e -> e.getItem().getItem() instanceof ItemAncientWill);

        for (ItemEntity e : list) {
            Item item = e.getItem().getItem();

            if (item == lastWill) {
                count++;
            }
            else {
                lastWill = item;
                count = 1;
            }

            addMana((int) (2400 * Math.pow(1.3, count - 1)));
            e.remove();
            break;
        }
    }

    @Override
    public int getRange() {
        return 2;
    }
}
