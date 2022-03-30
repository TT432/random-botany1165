package com.nmmoc7.randombotany.specialflower.generating;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import com.nmmoc7.randombotany.specialflower.generating.base.BaseGeneratingFlower;
import com.nmmoc7.randombotany.util.FlowerItemAB;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DustW
 */
public class WitchFlower extends BaseGeneratingFlower {
    private static final String TAG_COOLDOWN = "cooldown";
    private int cooldown;

    Map<Effect, Integer> map = new HashMap<>();

    public WitchFlower() {
        super(ModSpecialFlowers.WITCH.getType());
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (world == null || world.isRemote) {
            return;
        }

        if (cooldown-- <= 0) {
            List<ItemEntity> items = searchItems(itemEntity ->
                    itemEntity.getItem().getItem() instanceof PotionItem &&
                            itemEntity.getAge() > getSlowdownFactor());

            for (ItemEntity item : items) {
                List<EffectInstance> potions = PotionUtils.getEffectsFromStack(item.getItem());

                if (potions.isEmpty()) {
                    continue;
                }

                ArrayList<Effect> unRemoveList = new ArrayList<>();

                int mana = 0;

                for (EffectInstance potion : potions) {
                    Effect effect = potion.getPotion();

                    if (map.containsKey(effect)) {
                        int before = map.get(effect);
                        map.put(effect, before + 1);

                        mana += 1 / (before + 1) * 3500;
                    }
                    else {
                        map.put(effect, 1);

                        mana += 3500;
                    }

                    unRemoveList.add(effect);
                }

                addMana(mana);
                item.getItem().shrink(1);

                map.entrySet().removeIf(e -> !unRemoveList.contains(e.getKey()));

                item.playSound(SoundEvents.ENTITY_SPLASH_POTION_BREAK, 0.2F, 0.6F);
                ((ServerWorld) getWorld()).spawnParticle(ParticleTypes.ENTITY_EFFECT,
                        item.getPosX(), item.getPosY(), item.getPosZ(),
                        20,
                        0.1D, 0.1D, 0.1D,
                        0.05D
                );

                sync();
                break;
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
    public int getColor() {
        return 0xD3D604;
    }

    @Override
    public int getRange() {
        return 0;
    }
}
