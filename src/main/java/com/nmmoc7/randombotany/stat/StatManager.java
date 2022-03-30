package com.nmmoc7.randombotany.stat;

import com.nmmoc7.randombotany.specialflower.ModSpecialFlowers;
import net.minecraft.item.Item;

/**
 * @author DustW
 **/
public class StatManager {
    public static final IStatHolder<Item> WITCH_CRAFT = new FlowerCraftListenerStatHolder(ModSpecialFlowers.WITCH.getItem().getA());

    public static void register() {

    }
}
