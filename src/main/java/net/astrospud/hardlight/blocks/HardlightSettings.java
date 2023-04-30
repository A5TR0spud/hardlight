package net.astrospud.hardlight.blocks;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HardlightSettings {
    public boolean defaultEntityCollide = false;
    public List<Identifier> exceptEntityCollide = new ArrayList<>();
    public boolean defaultPlayerCollide = false;
    public List<UUID> exceptPlayerCollide = new ArrayList<>();
    public float red = 1f;
    public float green = 1f;
    public float blue = 1f;

    public HardlightSettings() {

    }
}
