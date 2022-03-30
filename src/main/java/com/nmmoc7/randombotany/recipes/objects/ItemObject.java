package com.nmmoc7.randombotany.recipes.objects;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author DustW
 **/
public class ItemObject implements IRecipeObject {
    Item item;

    public ItemObject(Item item) {
        this.item = item;
    }

    public ItemObject(Block block) {
        this.item = block.asItem();
    }

    @Override
    public JsonObject getJson() {
        JsonObject result = new JsonObject();
        result.addProperty("item", item.getRegistryName().toString());
        return result;
    }
}
