package com.nmmoc7.randombotany.specialflower;

import com.nmmoc7.randombotany.specialflower.functional.Citron;
import com.nmmoc7.randombotany.specialflower.functional.Curseater;
import com.nmmoc7.randombotany.specialflower.functional.Vacuity;
import com.nmmoc7.randombotany.specialflower.generating.TinyPotatoBelieverFlower;
import com.nmmoc7.randombotany.specialflower.generating.WitchFlower;
import com.nmmoc7.randombotany.util.FlowerAB;
import com.nmmoc7.randombotany.util.FlowerItemAB;
import com.nmmoc7.randombotany.util.FlowerObj;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import vazkii.botania.api.subtile.TileEntitySpecialFlower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ModSpecialFlowers {
    public static final Map<String, FlowerAB> MOD_FLOWERS = new HashMap<>();
    public static final ArrayList<FlowerItemAB> MOD_FLOWER_ITEMS = new ArrayList<>();
    public static final Map<String, TileEntityType<? extends TileEntitySpecialFlower>> MOD_FLOWER_TILES = new HashMap<>();

    public static final FlowerObj<TinyPotatoBelieverFlower> TINY_POTATO_BELIEVER =
            new FlowerObj<>("believer", Effects.HUNGER, 180, TinyPotatoBelieverFlower::new);

    public static final FlowerObj<WitchFlower> WITCH =
            new FlowerObj<>("witch", Effects.POISON, 180, WitchFlower::new);

    public static final FlowerObj<Citron> CITRON =
            new FlowerObj<>("citron", Effects.SPEED, 180, Citron::new);

    public static final FlowerObj<Vacuity> VACUITY =
            new FlowerObj<>("vacuity", Effects.WATER_BREATHING, 60, Vacuity::new);

    public static final FlowerObj<Curseater> CURSEATER =
            new FlowerObj<>("curseater", Effects.BLINDNESS, 10, Curseater::new);
}
