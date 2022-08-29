package net.sirobby.mods.islandchamp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Settings.SettingsLoader;
import net.sirobby.mods.islandchamp.Commands.CommandModule;
import net.sirobby.mods.islandchamp.Settings.SettingsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IslandChamp implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static SettingsManager settingsManager;

	public static final Logger LOGGER = LoggerFactory.getLogger("islandchamp");

	// Settings


	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		MinecraftClient mc = MinecraftClient.getInstance();

		new CommandModule().init(mc);
		new SettingsLoader().init(mc);
	}
}
