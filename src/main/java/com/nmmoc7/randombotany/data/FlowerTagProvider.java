package com.nmmoc7.randombotany.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FlowerTagProvider {
    static final ArrayList<String> floatingTags = new ArrayList<>();

    public static void a() {
        addTags();

        JsonObject floating = new JsonObject();
        floating.addProperty("replace", false);
        JsonArray values = new JsonArray();
        floatingTags.forEach(values::add);
        floating.add("values", values);
        save(getPathFloatingTag(output), floating);
    }

    private static void addTags() {
        ModSpecialFlowers.MOD_FLOWERS.values().forEach(flowerAB ->
                addFloating(flowerAB.getB()));
    }

    static void addFloating(Block block) {
        floatingTags.add(block.getRegistryName().toString());
    }

    static final Path output = Paths.get("C:\\Users\\q2437\\Desktop\\idea项目\\RandomBotany - Forge 1.16.5\\src\\generated\\resources");

    static void save(Path path, JsonObject json) {
        try {
            if (path.toFile().exists()) {
                return;
            }

            Files.createDirectories(path.getParent());
            try (BufferedWriter bufferedwriter = Files.newBufferedWriter(path)) {
                bufferedwriter.write(json.toString());
            }
            path.toFile().createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getPathFloatingTag(Path path) {
        return path.resolve("data/botania/tags/blocks/special_floating_flowers.json");
    }
}
