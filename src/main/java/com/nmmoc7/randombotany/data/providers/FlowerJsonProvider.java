package com.nmmoc7.randombotany.data.providers;

import com.nmmoc7.randombotany.data.jsons.*;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.data.DataGenerator;

public class FlowerJsonProvider extends DataGenProvider {
    public FlowerJsonProvider(DataGenerator gen) {
        super(
                gen,
                new BlockStateProvider(gen),
                new BlockModelProvider(gen),
                new ItemModelProvider(gen),
                new TagProvider(gen),
                new LootProvider(gen),
                new RecipesProvider(gen)
        );
    }

    private void addFlowers() {
        ModSpecialFlowers.MOD_FLOWERS.forEach((name, flower) -> put(name, flower.getA(), flower.getB()));
    }

    @Override
    protected void preGenerate() {
        addFlowers();
    }
}
