package com.nmmoc7.randombotany.recipes.objects;

import com.google.gson.JsonObject;

/**
 * @author DustW
 **/
public class TagObject implements IRecipeObject {
    String name;

    public TagObject(String name) {
        this.name = name;
    }

    @Override
    public JsonObject getJson() {
        JsonObject result = new JsonObject();
        result.addProperty("tag", "botania:" + name);
        return result;
    }
}
