package com.nmmoc7.randombotany.stat.type;

import com.nmmoc7.randombotany.RandomBotany;
import net.minecraft.item.Item;
import net.minecraft.stats.StatType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.IModBusEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @author DustW
 **/
public class StatTypesManager {
    private static final DeferredRegister<StatType<?>> TYPES = DeferredRegister.create(ForgeRegistries.STAT_TYPES, RandomBotany.MOD_ID);

    public static final RegistryObject<StatType<Item>> MAKE_FLOWER = TYPES.register("name", () -> new StatType<>(Registry.ITEM));

    public static void register(IEventBus bus) {
        TYPES.register(bus);
    }
}
