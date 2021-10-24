package com.nmmoc7.randombotany.event;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.client.render.tile.RenderTileSpecialFlower;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModelRegistryHandler {
    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        ModSpecialFlowers.MOD_FLOWER_TILES.values().forEach(tile ->
                ClientRegistry.bindTileEntityRenderer(tile, RenderTileSpecialFlower::new));
    }
}
