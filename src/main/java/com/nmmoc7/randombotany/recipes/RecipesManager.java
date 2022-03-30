package com.nmmoc7.randombotany.recipes;

import com.google.gson.JsonArray;
import com.nmmoc7.randombotany.recipes.objects.IRecipeObject;
import com.nmmoc7.randombotany.recipes.objects.ItemObject;
import com.nmmoc7.randombotany.recipes.objects.TagObject;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import vazkii.botania.common.block.ModBlocks;

import java.util.*;

/**
 * @author DustW
 **/
public class RecipesManager {
    private static final Map<String, List<IRecipeObject>> PETAL_APOTHECARY_RECIPES = new HashMap<>();

    private static final TagObject ORANGE = petals("orange");
    private static final TagObject GREEN = petals("green");
    private static final TagObject WHITE = petals("white");
    private static final TagObject PURPLE = petals("purple");
    private static final TagObject BLACK = petals("black");

    private static final TagObject MANA = rune("mana");
    private static final TagObject WRATH = rune("wrath");

    private static final ItemObject TINY_POTATO = new ItemObject(ModBlocks.tinyPotato);

    static {
        petal(ModSpecialFlowers.TINY_POTATO_BELIEVER.getName(),
                GREEN, GREEN,
                BLACK, BLACK,
                MANA,
                TINY_POTATO
        );

        petal(ModSpecialFlowers.WITCH.getName(),
                GREEN, GREEN,
                PURPLE, PURPLE,
                BLACK,
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

    private static TagObject rune(String name) {
        return new TagObject("runes/" + name);
    }

    private static TagObject petals(String name) {
        return new TagObject("petals/" + name);
    }

    public static JsonArray getPetal(String name) {
        List<IRecipeObject> list = PETAL_APOTHECARY_RECIPES.get(name);
        JsonArray result = new JsonArray();
        list.forEach(o -> result.add(o.getJson()));
        return result;
    }
}
