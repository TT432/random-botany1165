package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.recipes.RecipesManager;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.nio.file.Path;

/**
 * @author DustW
 **/
public class RecipesProvider extends AbstractFlowerJsonProvider {
    public RecipesProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected JsonObject getNormalJson(String name, Block normal, Block floating) {
        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", "botania:petal_apothecary");
        JsonObject output = new JsonObject();
        output.addProperty("item", normal.getRegistryName().toString());
        recipe.add("output", output);
        recipe.add("ingredients", RecipesManager.getPetal(name));
        return recipe;
    }

    /**
     * {
     *   "type": "minecraft:crafting_shapeless",
     *   "ingredients": [
     *     {
     *       "tag": "botania:floating_flowers"
     *     },
     *     {
     *       "item": "botania:bergamute"
     *     }
     *   ],
     *   "result": {
     *     "item": "botania:floating_bergamute"
     *   }
     * }
     */
    @Override
    protected JsonObject getFloatingJson(String name, Block normal, Block floating) {
        JsonObject result = new JsonObject();
        result.addProperty("type", "minecraft:crafting_shapeless");
        JsonArray ingredients = new JsonArray();
        JsonObject ingredients1 = new JsonObject();
        ingredients1.addProperty("tag", "botania:floating_flowers");
        ingredients.add(ingredients1);
        JsonObject ingredients2 = new JsonObject();
        ingredients2.addProperty("item", normal.getRegistryName().toString());
        ingredients.add(ingredients2);
        result.add("ingredients", ingredients);
        JsonObject result1 = new JsonObject();
        result1.addProperty("item", floating.getRegistryName().toString());
        result.add("result", result1);
        return result;
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return dataPath("recipes", resourceLocation);
    }
}
