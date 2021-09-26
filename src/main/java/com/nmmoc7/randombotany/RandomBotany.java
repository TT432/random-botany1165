package com.nmmoc7.randombotany;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RandomBotany.MOD_ID)
public class RandomBotany {
    public static final String MOD_ID = "random-botany";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public RandomBotany() {
    }
}
