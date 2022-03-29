package com.nmmoc7.randombotany.data.jsons;

import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author DustW
 **/
public abstract class AbstractJsonProvider {
    DataGenerator gen;

    public AbstractJsonProvider(DataGenerator gen) {
        this.gen = gen;
    }

    public abstract void put(String name, Block normal, Block floating);

    protected abstract Path getPath(ResourceLocation resourceLocation);
    public abstract void generate();

    protected Path out() {
        return gen.getOutputFolder().resolve("../../main/resources");
    }

    protected Path normalPath(String path, ResourceLocation resourceLocation) {
        return out().resolve("assets" + resourceLocation.getPath() + "/" + path + "/" + resourceLocation.getPath() + ".json");
    }

    protected Path dataPath(String path, ResourceLocation resourceLocation) {
        return out().resolve("data" + resourceLocation.getPath() + "/" + path + "/" + resourceLocation.getPath() + ".json");
    }

    protected void save(Path path, JsonObject json) {
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
}
