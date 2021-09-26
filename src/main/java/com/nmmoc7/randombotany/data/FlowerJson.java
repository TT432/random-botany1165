package com.nmmoc7.randombotany.data;

import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.RandomBotany;
import net.minecraft.block.Block;

public class FlowerJson {
    /**
     * {
     *   "variants": {
     *     "": {
     *       "model": "botania:block/gourmaryllis"
     *     }
     *   }
     * }
     */
    public static JsonObject flowerBlockState(Block block) {
        JsonObject result = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject none = new JsonObject();
        none.addProperty("model", RandomBotany.MOD_ID + ":block/" + block.getRegistryName().getPath());
        variants.add("", none);
        result.add("variants", variants);
        return result;
    }

    /**
     * {
     *   "parent": "botania:block/shapes/cross",
     *   "textures": {
     *     "cross": "botania:block/gourmaryllis"
     *   }
     * }
     */
    public static JsonObject flowerModel(Block block) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "botania:block/shapes/cross");
        JsonObject textures = new JsonObject();
        textures.addProperty("cross", RandomBotany.MOD_ID + ":block/" + block.getRegistryName().getPath());
        result.add("textures", textures);
        return result;
    }

    /**
     * {
     *   "parent": "minecraft:item/generated",
     *   "textures": {
     *     "layer0": "botania:block/gourmaryllis"
     *   }
     * }
     */
    public static JsonObject flowerItem(Block block) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "minecraft:item/generated");
        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", RandomBotany.MOD_ID + ":block/" + block.getRegistryName().getPath());
        result.add("textures", textures);
        return result;
    }

    /**
     * {
     *   "parent": "minecraft:block/block",
     *   "loader": "botania:floating_flower",
     *   "flower": {
     *     "parent": "botania:block/gourmaryllis"
     *   }
     * }
     */
    public static JsonObject flowerBlockModelFloating(Block block) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", "minecraft:block/block");
        result.addProperty("loader", "botania:floating_flower");
        JsonObject flower = new JsonObject();
        flower.addProperty("parent", RandomBotany.MOD_ID + ":block/" + block.getRegistryName().getPath());
        result.add("flower", flower);
        return result;
    }

    /**
     * {
     *   "parent": "botania:block/floating_gourmaryllis"
     * }
     */
    public static JsonObject flowerBlockItemFloating(Block block) {
        JsonObject result = new JsonObject();
        result.addProperty("parent", RandomBotany.MOD_ID + ":block/" + block.getRegistryName().getPath());
        return result;
    }
}
