package com.nmmoc7.randombotany.stat;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.StatType;

/**
 * @author DustW
 **/
public interface IStatHolder<TYPE> {
    TYPE getItem();
    StatType<TYPE> getStatType();

    default void add(PlayerEntity player, int value) {
        player.addStat(getStatType().get(getItem()), value);
    }

    default void add(PlayerEntity player) {
        player.addStat(getStatType().get(getItem()));
    }

    default int get(ServerPlayerEntity player) {
        return player.getStats().getValue(getStatType().get(getItem()));
    }
}
