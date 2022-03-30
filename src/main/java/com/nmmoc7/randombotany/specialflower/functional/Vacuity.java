package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectUtils;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

/**
 * @author DustW
 **/
public class Vacuity extends BaseFunctionalFlower {
    public Vacuity() {
        super(ModSpecialFlowers.VACUITY.getType());
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world != null && !world.isRemote && getMana() >= cost()) {
            List<LivingEntity> list = searchEntities(LivingEntity.class, null);

            addMana(-cost());

            list.forEach(e -> {
                if (canAttack(e)) {
                    int air = e.getAir();
                    air = decreaseAirSupply(e, air);
                    e.setAir(air);
                    if (e.getAir() <= -20) {
                        e.setAir(0);
                        Vector3d vector3d = e.getMotion();

                        for(int i = 0; i < 8; ++i) {
                            double d2 = world.rand.nextDouble() - world.rand.nextDouble();
                            double d3 = world.rand.nextDouble() - world.rand.nextDouble();
                            double d4 = world.rand.nextDouble() - world.rand.nextDouble();
                            this.world.addParticle(ParticleTypes.BUBBLE,
                                    e.getPosX() + d2, e.getPosY() + d3, e.getPosZ() + d4,
                                    vector3d.x, vector3d.y, vector3d.z);
                        }

                        e.attackEntityFrom(DamageSource.DROWN, 2.0F);
                    }
                }
            });
        }
    }

    boolean canAttack(LivingEntity entity) {
        return !(entity instanceof PlayerEntity  && ((PlayerEntity) entity).abilities.disableDamage);
    }

    int decreaseAirSupply(LivingEntity e, int air) {
        int i = EnchantmentHelper.getRespirationModifier(e);
        return i > 0 && world.rand.nextInt(i + 1) > 0 ? air : air - 5;
    }

    @Override
    public int getRange() {
        return 4;
    }

    @Override
    public int cost() {
        return 1000;
    }
}
