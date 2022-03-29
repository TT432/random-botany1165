package com.nmmoc7.randombotany.specialflower.functional;

import com.nmmoc7.randombotany.specialflower.functional.base.BaseFunctionalFlower;
import net.minecraft.tileentity.TileEntityType;

/**
 * @author DustW
 **/
public class Citron extends BaseFunctionalFlower {
    public Citron(TileEntityType<?> type) {
        super(type);
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int cost() {
        return 0;
    }
}
