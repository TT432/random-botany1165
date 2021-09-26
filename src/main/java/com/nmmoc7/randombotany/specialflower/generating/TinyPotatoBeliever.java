package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.client.model.data.IModelData;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;

import javax.annotation.Nonnull;
import java.util.List;

public class TinyPotatoBeliever extends TileEntityGeneratingFlower {
    private static final int RANGE = 1;

    private static final String TAG_COOLDOWN = "cooldown";
    int cooldown = 0;

    private static final String TAG_COUNT = "count";
    int tinyPotatoCount = 0;

    public TinyPotatoBeliever() {
        super(ModSpecialFlowers.TINY_POTATO_BELIEVER);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote)
            return;

        if(cooldown > 0)
            cooldown--;

        if(tinyPotatoCount != 0 && cooldown == 0) {
            if (--tinyPotatoCount != 0)
                cooldown = tinyPotatoCount > 32 ? 50 : 100;

            addMana(2157);

            getWorld().playSound(null, getPos(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.BLOCKS, 1, 0.1F);
            sync();
        }

        int slowdown = getSlowdownFactor();

        List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class,
                        new AxisAlignedBB(getPos().add(-RANGE, -RANGE, -RANGE),
                                getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)),
                        (item) -> {
                            ItemStack stack = item.getItem();
                            return !stack.isEmpty() &&
                                    stack.getItem() instanceof ItemBlockTinyPotato &&
                                    item.isAlive() &&
                                    item.getAge() >= slowdown;
                        });

        for(ItemEntity item : items) {
            ItemStack stack = item.getItem();

            if(tinyPotatoCount <= 64) {
                tinyPotatoCount += stack.getCount();
                item.playSound(SoundEvents.ENTITY_GENERIC_EAT, 0.2F, 0.6F);
                sync();
                ((ServerWorld) getWorld()).spawnParticle(new ItemParticleData(ParticleTypes.ITEM, stack),
                        item.getPosX(), item.getPosY(), item.getPosZ(),
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );
            }

            item.remove();
        }
    }

    @Override
    public void writeToPacketNBT(CompoundNBT cmp) {
        super.writeToPacketNBT(cmp);
        cmp.putInt(TAG_COOLDOWN, cooldown);
        cmp.putInt(TAG_COUNT, tinyPotatoCount);
    }

    @Override
    public void readFromPacketNBT(CompoundNBT cmp) {
        super.readFromPacketNBT(cmp);
        cooldown = cmp.getInt(TAG_COOLDOWN);
        tinyPotatoCount = cmp.getInt(TAG_COUNT);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(getEffectivePos(), RANGE);
    }

    @Override
    public int getMaxMana() {
        return 90000;
    }

    @Override
    public int getColor() {
        return 0xD3D604;
    }
}
