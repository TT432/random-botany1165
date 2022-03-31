package com.nmmoc7.randombotany.recipes;

import com.google.gson.JsonArray;
import com.nmmoc7.randombotany.recipes.objects.IRecipeObject;
import com.nmmoc7.randombotany.recipes.objects.ItemObject;
import com.nmmoc7.randombotany.recipes.objects.TagObject;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.item.Items;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

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
    private static final TagObject LIGHT_GRAY = petals("light_gray");
    private static final TagObject RED = petals("red");

    private static final TagObject MANA = rune("mana");
    private static final TagObject WRATH = rune("wrath");
    private static final TagObject GLUTTONY = rune("gluttony");
    private static final TagObject PRIDE = rune("pride");

    private static final ItemObject TINY_POTATO = new ItemObject(ModBlocks.tinyPotato);
    private static final ItemObject ENCHANTED_BOOK = new ItemObject(Items.ENCHANTED_BOOK);

    private static final ItemObject WILL1 = new ItemObject(ModItems.ancientWillAhrim);
    private static final ItemObject WILL2 = new ItemObject(ModItems.ancientWillDharok);
    private static final ItemObject WILL3 = new ItemObject(ModItems.ancientWillGuthan);
    private static final ItemObject WILL4 = new ItemObject(ModItems.ancientWillKaril);
    private static final ItemObject WILL5 = new ItemObject(ModItems.ancientWillTorag);
    private static final ItemObject WILL6 = new ItemObject(ModItems.ancientWillVerac);

    private static final ItemObject LIFE_ESSENCE = new ItemObject(ModItems.lifeEssence);

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

        petal(ModSpecialFlowers.VACUITY.getName(),
                WHITE, WHITE, WHITE, WHITE,
                WHITE, WHITE, WHITE,
                MANA
        );

        petal(ModSpecialFlowers.CURSEATER.getName(),
                ENCHANTED_BOOK,
                LIGHT_GRAY, LIGHT_GRAY, LIGHT_GRAY,
                GLUTTONY, GLUTTONY, GLUTTONY,
                RED, RED, RED, RED,
                RED, RED, RED
        );

        petal(ModSpecialFlowers.WILLEATER.getName(),
                WILL1,
                WILL2,
                WILL3,
                WILL4,
                WILL5,
                WILL6,
                LIFE_ESSENCE, LIFE_ESSENCE, LIFE_ESSENCE, LIFE_ESSENCE,
                PRIDE, PRIDE,
                GREEN, GREEN, GREEN, GREEN
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
        JsonArray result = new JsonArray();
        if (PETAL_APOTHECARY_RECIPES.containsKey(name)) {
            List<IRecipeObject> list = PETAL_APOTHECARY_RECIPES.get(name);
            list.forEach(o -> result.add(o.getJson()));
        }
        return result;
    }
}
