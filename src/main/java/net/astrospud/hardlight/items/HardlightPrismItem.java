package net.astrospud.hardlight.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HardlightPrismItem extends Item {
    public HardlightPrismItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
