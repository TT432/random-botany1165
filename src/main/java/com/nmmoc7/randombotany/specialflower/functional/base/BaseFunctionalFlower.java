package com.nmmoc7.randombotany.specialflower.functional.base;

import net.minecraft.tileentity.TileEntityType;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityFunctionalFlower;

/**
 * @author DustW
 */
public abstract class BaseFunctionalFlower extends TileEntityFunctionalFlower {
    public BaseFunctionalFlower(TileEntityType<?> type) {
        super(type);
    }

    /**
     * 获取作用范围
     * @return range
     */
    abstract public int getRange();

    /**
     * 获取消耗
     * @return cost
     */
    abstract public int cost();

    @Override
    public int getMaxMana() {
        return cost();
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(getEffectivePos(), getRange());
    }
}
