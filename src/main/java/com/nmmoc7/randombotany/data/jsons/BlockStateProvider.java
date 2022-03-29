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
public class BlockStateProvider extends AbstractFlowerJsonProvider {
    public BlockStateProvider(DataGenerator gen) {
        super(gen);
    }

    /**
     * {
     *   "variants": {
     *     "": {
     *       "model": "botania:block/gourmaryllis"
     *     }
     *   }
     * }
     */
    @Override
    protected JsonObject getNormalJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        JsonObject variants = new JsonObject();
        JsonObject none = new JsonObject();
        none.addProperty("model", RandomBotany.MOD_ID + ":block/" + name);
        variants.add("", none);
        result.add("variants", variants);
        return result;
    }

    @Override
    protected JsonObject getFloatingJson(String name, Block normal, Block floating) {
        return getNormalJson(name, normal, floating);
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return normalPath("blockstates", resourceLocation);
    }
}
