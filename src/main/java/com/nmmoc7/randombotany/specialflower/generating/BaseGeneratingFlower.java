package com.nmmoc7.randombotany.specialflower.generating;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.TileEntityGeneratingFlower;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author DustW
 */
public abstract class BaseGeneratingFlower extends TileEntityGeneratingFlower {
    public BaseGeneratingFlower(TileEntityType<?> type) {
        super(type);
    }

    /**
     * 获取作用范围
     * @return range
     */
    abstract int getRange();

    protected List<ItemEntity> searchItems(@Nullable Predicate<ItemEntity> filter) {
        return world.getEntitiesWithinAABB(ItemEntity.class,
                new AxisAlignedBB(getPos().add(-getRange(), -getRange(), -getRange()),
                        getPos().add(getRange() + 1, getRange() + 1, getRange() + 1)),
                (item) ->
                        !item.getItem().isEmpty() && item.isAlive() && (filter == null || filter.test(item)));
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(getEffectivePos(), getRange());
    }
}
