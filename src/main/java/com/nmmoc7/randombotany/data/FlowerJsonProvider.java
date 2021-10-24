package com.nmmoc7.randombotany.data;

import com.google.gson.JsonObject;
import com.nmmoc7.randombotany.RandomBotany;
import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FlowerJsonProvider {
    static Map<String, JsonObject> flowerJsonBlockState = new HashMap<>();
    static Map<String, JsonObject> flowerJsonModel = new HashMap<>();
    static Map<String, JsonObject> flowerItemJson = new HashMap<>();

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

    static final Path output = Paths.get("C:\\Users\\q2437\\Desktop\\idea项目\\RandomBotany - Forge 1.16.5\\src\\generated\\resources");

    public static void a() {
        addFlowers();

        for (Map.Entry<String, JsonObject> json : flowerJsonBlockState.entrySet()) {
            save(getPathBlockState(output, new ResourceLocation(RandomBotany.MOD_ID, json.getKey())), json.getValue());
        }

        for (Map.Entry<String, JsonObject> json : flowerJsonModel.entrySet()) {
            save(getPathModel(output, new ResourceLocation(RandomBotany.MOD_ID, json.getKey())), json.getValue());
        }

        for (Map.Entry<String, JsonObject> json : flowerItemJson.entrySet()) {
            save(getPathItem(output, new ResourceLocation(RandomBotany.MOD_ID, json.getKey())), json.getValue());
        }
    }

    private static void put(String name, Block block) {
        flowerJsonModel.put(name, FlowerJson.flowerModel(block));
        flowerJsonBlockState.put(name, FlowerJson.flowerBlockState(block));
        flowerItemJson.put(name, FlowerJson.flowerItem(block));
    }

    private static void putFloating(String name, Block unFloating, Block block) {
        flowerJsonModel.put(name, FlowerJson.flowerBlockModelFloating(unFloating));
        flowerJsonBlockState.put(name, FlowerJson.flowerBlockState(block));
        flowerItemJson.put(name, FlowerJson.flowerBlockItemFloating(block));
    }

    private static void addFlowers() {
        ModSpecialFlowers.MOD_FLOWERS.forEach((name, flower) -> {
            put(name, flower.getA());
            putFloating(name + "_floating", flower.getA(), flower.getB());
        });
    }

    private static Path getPathBlockState(Path path, ResourceLocation resourceLocation) {
        return path.resolve("assets/" + resourceLocation.getNamespace() + "/blockstates/" + resourceLocation.getPath() + ".json");
    }

    private static Path getPathModel(Path path, ResourceLocation resourceLocation) {
        return path.resolve("assets/" + resourceLocation.getNamespace() + "/models/block/" + resourceLocation.getPath() + ".json");
    }

    private static Path getPathItem(Path path, ResourceLocation resourceLocation) {
        return path.resolve("assets/" + resourceLocation.getNamespace() + "/models/item/" + resourceLocation.getPath() + ".json");
    }
}
