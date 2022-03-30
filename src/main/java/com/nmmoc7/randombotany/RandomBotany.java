package com.nmmoc7.randombotany;

import com.nmmoc7.randombotany.stat.StatManager;
import com.nmmoc7.randombotany.stat.type.StatTypesManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(RandomBotany.MOD_ID)
public class RandomBotany {
    public static final String MOD_ID = "random-botany";

    private static final Logger LOGGER = LogManager.getLogger();

    public RandomBotany() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        StatTypesManager.register(bus);
        StatManager.register();
    }
}
