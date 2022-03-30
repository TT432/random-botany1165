package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.RandomBotany;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DustW
 **/
public abstract class AbstractFlowerJsonProvider extends AbstractJsonProvider {
    private final Map<String, JsonObject> map = new HashMap<>();

    public AbstractFlowerJsonProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    public void put(String name, Block normal, Block floating) {
        map.put(name, getNormalJson(name, normal, floating));
        String fName = name + "_floating";
        map.put(fName, getFloatingJson(fName, normal, floating));
    }

    @Override
    public void generate() {
        map.forEach((s, j) -> save(getPath(new ResourceLocation(RandomBotany.MOD_ID, s)), j));
    }

    protected abstract JsonObject getNormalJson(String name, Block normal, Block floating);
    protected abstract JsonObject getFloatingJson(String name, Block normal, Block floating);
}
