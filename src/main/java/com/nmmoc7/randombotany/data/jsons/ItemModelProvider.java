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
public class ItemModelProvider extends AbstractFlowerJsonProvider {
    public ItemModelProvider(DataGenerator gen) {
        super(gen);
    }

    /**
     * {
     *   "parent": "minecraft:item/generated",
     *   "textures": {
     *     "layer0": "botania:block/gourmaryllis"
     *   }
     * }
     */
    @Override
    protected JsonObject getNormalJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "minecraft:item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", RandomBotany.MOD_ID + ":block/" + name);
        result.add("textures", textures);
        return result;
    }
    /**
     * {
     *   "parent": "botania:block/floating_gourmaryllis"
     * }
     */
    @Override
    protected JsonObject getFloatingJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", RandomBotany.MOD_ID + ":block/" + name);
        return result;
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return normalPath("models/item", resourceLocation);
    }
}
