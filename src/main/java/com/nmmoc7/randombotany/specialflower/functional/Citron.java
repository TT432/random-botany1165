package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class Citron extends BaseFunctionalFlower {
    private final List<BlockPos> needParticle = new ArrayList<>();

    public Citron() {
        super(ModSpecialFlowers.CITRON.getType());
    }

    long time;

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote || world.getGameTime() == time) {
            return;
        }

        time = world.getGameTime();

        List<TileEntity> list = searchTileEntity(getRange(), getRange(), null);

        for (TileEntity tileEntity : list) {
            if (!tileEntity.isRemoved() && tileEntity instanceof ITickableTileEntity) {
                if (getMana() > cost()) {
                    addMana(-cost());

                    if (!needParticle.contains(tileEntity.getPos())) {
                        needParticle.add(tileEntity.getPos());
                    }

                    ((ITickableTileEntity) tileEntity).tick();
                    ((ITickableTileEntity) tileEntity).tick();
                    ((ITickableTileEntity) tileEntity).tick();
                }
            }
        }

        if (ticksExisted % 40 == 0) {
            needParticle.forEach(p -> {
                ((ServerWorld) getWorld()).spawnParticle(ParticleTypes.CLOUD,
                        p.getX() + .5, p.getY() + .5, p.getZ() + .5,
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );
            });
            needParticle.clear();
        }
    }

    @Override
    public int getRange() {
        return 3;
    }

    @Override
    public int getMaxMana() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int cost() {
        return 35000;
    }
}
