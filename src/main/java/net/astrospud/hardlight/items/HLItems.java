package net.astrospud.hardlight.items;

import net.astrospud.hardlight.HardlightMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HLItems {
    public static Item HARDLIGHT_PRISM;

    public static Item registerItem(Item item, String name) {
        return Registry.register(Registry.ITEM, new Identifier(HardlightMod.MODID, name), item);
    }

    public static void init() {
        HardlightMod.LOGGER.info("registering items");
        HARDLIGHT_PRISM = registerItem(new HardlightPrismItem(new FabricItemSettings().group(ItemGroup.MATERIALS)), "hardlight_prism");
        HardlightMod.LOGGER.info("registered items");
    }
}
