package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;

import java.util.List;

public class TinyPotatoBelieverFlower extends BaseGeneratingFlower {
    private static final String TAG_COOLDOWN = "cooldown";
    int cooldown = 0;

    public TinyPotatoBelieverFlower() {
        super(ModSpecialFlowers.TINY_POTATO_BELIEVER.getType());
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote) {
            return;
        }

        if(cooldown > 0) {
            cooldown--;
        }

        List<ItemEntity> items = searchItems(item ->
                item.getItem().getItem() instanceof ItemBlockTinyPotato && item.getAge() >= getSlowdownFactor());

        for(ItemEntity item : items) {
            ItemStack stack = item.getItem();

            stack.shrink(1);
            addMana(1452);

            item.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.2F, 0.6F);
            ((ServerWorld) getWorld()).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, stack),
                    item.getPosX(), item.getPosY(), item.getPosZ(),
                    20,
                    0.1D, 0.1D, 0.1D,
                    0.05D
            );

            sync();
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
    public int getColor() {
        return 0xD3D604;
    }

    @Override
    public int getRange() {
        return 1;
    }
}
