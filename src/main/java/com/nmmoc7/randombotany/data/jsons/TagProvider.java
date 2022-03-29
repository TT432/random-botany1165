package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DustW
 **/
public class TagProvider extends AbstractJsonProvider {
    public TagProvider(DataGenerator gen) {
        super(gen);
    }

    List<String> tags = new ArrayList<>();

    @Override
    public void put(String name, Block normal, Block floating) {
        tags.add(floating.getRegistryName().toString());
    }

    @Override
    protected Path getPath(ResourceLocation resourceLocation) {
        return out().resolve("data/botania/tags/blocks/special_floating_flowers.json");
    }

    @Override
    public void generate() {
        JsonObject floating = new JsonObject();
        floating.addProperty("replace", false);
        JsonArray values = new JsonArray();
        tags.forEach(values::add);
        floating.add("values", values);
        save(getPath(new ResourceLocation("")), floating);
    }
}
