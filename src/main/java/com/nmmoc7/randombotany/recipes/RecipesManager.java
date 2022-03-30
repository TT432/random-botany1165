package com.nmmoc7.randombotany.recipes;

import com.google.gson.JsonArray;
import com.nmmoc7.randombotany.recipes.objects.IRecipeObject;
import com.nmmoc7.randombotany.recipes.objects.ItemObject;
import com.nmmoc7.randombotany.recipes.objects.TagObject;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import org.lwjgl.system.CallbackI;
import vazkii.botania.common.block.ModBlocks;

import java.util.*;

/**
 * @author DustW
 **/
public class RecipesManager {
    private static final Map<String, List<IRecipeObject>> PETAL_APOTHECARY_RECIPES = new HashMap<>();

    private static final TagObject ORANGE = new TagObject("petals/orange");
    private static final TagObject GREEN = new TagObject("petals/green");
    private static final TagObject WHITE = new TagObject("petals/white");
    private static final TagObject purple = new TagObject("petals/purple");
    private static final TagObject black = new TagObject("petals/black");

    private static final TagObject MANA = new TagObject("runes/mana");
    private static final TagObject WRATH = new TagObject("runes/wrath");

    private static final ItemObject TINY_POTATO = new ItemObject(ModBlocks.tinyPotato);

    static {
        petal(ModSpecialFlowers.TINY_POTATO_BELIEVER.getName(),
                GREEN, GREEN,
                black, black,
                MANA,
                TINY_POTATO
        );

        petal(ModSpecialFlowers.WITCH.getName(),
                GREEN, GREEN,
                purple, purple,
                black,
                MANA
        );

        petal(ModSpecialFlowers.CITRON.getName(),
                ORANGE, ORANGE, ORANGE, ORANGE,
                ORANGE, ORANGE, ORANGE,
                GREEN, GREEN,
                WHITE,
                MANA,
                WRATH
        );
    }

    private static void petal(String name, IRecipeObject... recipeObjs) {
        PETAL_APOTHECARY_RECIPES.put(name, Arrays.asList(recipeObjs));
    }

    public static JsonArray getPetal(String name) {
        List<IRecipeObject> list = PETAL_APOTHECARY_RECIPES.get(name);
        JsonArray result = new JsonArray();
        list.forEach(o -> result.add(o.getJson()));
        return result;
    }
}
