package com.nmmoc7.randombotany.util;

import com.nmmoc7.randombotany.block.FloatingSpecialFlowerBlock;
import com.nmmoc7.randombotany.block.SpecialFlowerBlock;
import net.minecraftforge.common.util.Lazy;

public class FlowerAB extends LazyAB<SpecialFlowerBlock, FloatingSpecialFlowerBlock> {
    public FlowerAB(Lazy<SpecialFlowerBlock> aObj, Lazy<FloatingSpecialFlowerBlock> bObj) {
        super(aObj, bObj);
    }
}
