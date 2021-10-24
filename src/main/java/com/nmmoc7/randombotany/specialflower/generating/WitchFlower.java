package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.server.ServerWorld;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;

import java.util.List;

/**
 * @author DustW
 */
public class WitchFlower extends BaseGeneratingFlower {
    private static final String TAG_COOLDOWN = "cooldown";
    private int cooldown;

    private EffectType a = null;

    public WitchFlower() {
        super(ModSpecialFlowers.WITCH.getType());
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world.isRemote) {
            return;
        }

        cooldown = Math.max(cooldown - 1, 0);

        if (cooldown <= 0) {
            List<ItemEntity> items = searchItems(itemEntity ->
                    itemEntity.getItem().getItem() instanceof PotionItem &&
                            itemEntity.getAge() > getSlowdownFactor());

            for (ItemEntity item : items) {
                List<EffectInstance> potions = PotionUtils.getEffectsFromStack(item.getItem());

                if (potions.isEmpty()) {
                    continue;
                }

                EffectInstance potion = potions.get(0);

                if (a == null) {
                    a = potion.getPotion().getEffectType();
                }
                else {
                    if (a != potion.getPotion().getEffectType()) {
                        a = null;
                        addMana(1357);
                        cooldown = 30;
                    }
                    else {
                        a = potion.getPotion().getEffectType();
                        addMana(376);
                        cooldown = 10;
                    }
                }

                item.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 0.2F, 0.6F);
                sync();
                ((ServerWorld) getWorld()).spawnParticle(ParticleTypes.ENTITY_EFFECT,
                        item.getPosX(), item.getPosY(), item.getPosZ(),
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );

                item.remove();
            }
        }
    }

    @Override
    public int getSlowdownFactor() {
        return 5;
    }

    @Override
    public void writeToPacketNBT(CompoundNBT cmp) {
        super.writeToPacketNBT(cmp);
        cmp.putInt(TAG_COOLDOWN, cooldown);
    }

    @Override
    public void readFromPacketNBT(CompoundNBT cmp) {
        super.readFromPacketNBT(cmp);
        cooldown = cmp.getInt(TAG_COOLDOWN);
    }

    @Override
    public int getMaxMana() {
        return 9000;
    }

    @Override
    public int getColor() {
        return 0xD3D604;
    }

    @Override
    int getRange() {
        return 0;
    }
}
