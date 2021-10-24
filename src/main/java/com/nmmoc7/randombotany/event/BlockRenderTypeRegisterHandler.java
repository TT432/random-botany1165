package com.nmmoc7.randombotany.event;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRenderTypeRegisterHandler {
    @SubscribeEvent
    public static void onRenderTypeSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModSpecialFlowers.MOD_FLOWERS.values().forEach(flowerAB -> {
                RenderTypeLookup.setRenderLayer(flowerAB.getA(), RenderType.getCutout());
            });
        });
    }
}
