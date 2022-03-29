package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.RandomBotany;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.nio.file.Path;

/**
 * @author DustW
 **/
public class BlockModelProvider extends AbstractFlowerJsonProvider {
    public BlockModelProvider(DataGenerator gen) {
        super(gen);
    }

    /**
     * {
     *   "parent": "botania:block/shapes/cross",
     *   "textures": {
     *     "cross": "botania:block/gourmaryllis"
     *   }
     * }
     */
    @Override
    protected JsonObject getNormalJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "botania:block/shapes/cross");
        JsonObject textures = new JsonObject();
        textures.addProperty("cross", RandomBotany.MOD_ID + ":block/" + name);
        result.add("textures", textures);
        return result;
    }

    @Override
    protected JsonObject getFloatingJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "minecraft:block/block");
        result.addProperty("loader", "botania:floating_flower");
        JsonObject flower = new JsonObject();
        flower.addProperty("parent", RandomBotany.MOD_ID + ":block/" + normal.getRegistryName().getPath());
        result.add("flower", flower);
        return result;
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return normalPath("models/block", resourceLocation);
    }
}
