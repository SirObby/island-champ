package net.sirobby.mods.islandchamp.Settings;

import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.IslandChamp;
import net.sirobby.mods.islandchamp.Module;

public class SettingsLoader implements Module {
    @Override
    public void init(MinecraftClient mc) {
        IslandChamp.settingsManager = new SettingsManager();
    }
}
