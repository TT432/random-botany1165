package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;

import java.util.List;
import java.util.Map;

/**
 * @author DustW
 **/
public class Curseater extends BaseFunctionalFlower {
    public Curseater() {
        super(ModSpecialFlowers.CURSEATER.getType());
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote || ticksExisted % 40 != 0) {
            return;
        }

        List<PlayerEntity> list = searchEntities(PlayerEntity.class, null);

        thisTick :
        for (PlayerEntity player : list) {
            Iterable<ItemStack> armors = player.getArmorInventoryList();

            for (ItemStack armor : armors) {
                Map<Enchantment, Integer> enchants = EnchantmentHelper.getEnchantments(armor);

                for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                    Enchantment enchant = entry.getKey();
                    int level = entry.getValue();

                    if (enchant.isCurse()) {
                        if (getMana() > cost() * level * Math.pow(1.2, level - 1)) {
                            int newLevel = level - 1;

                            if (newLevel == 0) {
                                enchants.remove(enchant);
                            } else {
                                enchants.put(enchant, newLevel);
                            }

                            EnchantmentHelper.setEnchantments(enchants, armor);
                            break thisTick;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getMaxMana() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getRange() {
        return 2;
    }

    @Override
    public int cost() {
        return 10000;
    }
}
