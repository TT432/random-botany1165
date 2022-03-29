package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.RandomBotany;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.nio.file.Path;

/**
 * @author DustW
 **/
public class LootProvider extends AbstractFlowerJsonProvider {
    public LootProvider(DataGenerator gen) {
        super(gen);
    }

    /**
     * {
     *   "type": "minecraft:block",
     *   "pools": [
     *     {
     *       "name": "main",
     *       "rolls": 1,
     *       "entries": [
     *         {
     *           "type": "minecraft:item",
     *           "name": "botania:loonium"
     *         }
     *       ],
     *       "conditions": [
     *         {
     *           "condition": "minecraft:survives_explosion"
     *         }
     *       ]
     *     }
     *   ]
     * }
     */
    @Override
    protected JsonObject getNormalJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("type", "minecraft:block");

        JsonArray pools = new JsonArray();
        JsonObject pools1 = new JsonObject();

        pools1.addProperty("name", "main");
        pools1.addProperty("rolls", 1);

        JsonArray entries = new JsonArray();
        JsonObject entries1 = new JsonObject();
        entries1.addProperty("type", "minecraft:item");
        entries1.addProperty("name", new ResourceLocation(RandomBotany.MOD_ID, name).toString());
        entries.add(entries1);
        pools1.add("entries", entries);
        pools.add(pools1);

        JsonArray conditions = new JsonArray();
        JsonObject conditions1 = new JsonObject();
        conditions1.addProperty("condition", "minecraft:survives_explosion");
        conditions.add(conditions1);
        pools1.add("conditions", conditions);

        result.add("pools", pools);
        return result;
    }

    @Override
    protected JsonObject getFloatingJson(String name, Block normal, Block floating) {
        return getNormalJson(name, normal, floating);
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return dataPath("loot_tables", resourceLocation);
    }
}
