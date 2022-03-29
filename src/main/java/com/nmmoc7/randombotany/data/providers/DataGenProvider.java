package com.nmmoc7.randombotany.data.providers;

import com.nmmoc7.randombotany.data.jsons.AbstractJsonProvider;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;

/**
 * @author DustW
 **/
public abstract class DataGenProvider {
    protected DataGenerator gen;
    protected AbstractJsonProvider[] providers;

    public DataGenProvider(DataGenerator gen, AbstractJsonProvider... providers) {
        this.gen = gen;
        this.providers = providers;
    }

    protected abstract void preGenerate();

    protected void put(String name, Block normal, Block floating) {
        for (AbstractJsonProvider provider : providers) {
            provider.put(name, normal, floating);
        }
    }

    public void generate() {
        for (AbstractJsonProvider provider : providers) {
            provider.generate();
        }
    }
}
