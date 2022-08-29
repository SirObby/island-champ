package net.sirobby.mods.islandchamp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Commands.Command;
import net.sirobby.mods.islandchamp.Commands.CommandModule;
import net.sirobby.mods.islandchamp.Configs.ClothConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IslandChamp implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("islandchamp");

	// Settings
	public static boolean mod_enabled = true; // Automatic enable.

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		MinecraftClient mc = MinecraftClient.getInstance();

		new CommandModule().init(mc);

		try {
			Path p = FabricLoader.getInstance().getConfigDir().resolve("islandchamp.json");


			String c = Files.readString(p);

			JsonObject jsonObject = new Gson().fromJson(c, JsonObject.class);

			if(!jsonObject.isJsonObject()) {
				// Set default settings

				mod_enabled = true;

				ClothConfig.saveConfigs();
			} else {
				mod_enabled = jsonObject.get("enabled").getAsBoolean();
			}

		} catch (IOException e) {

			//throw new RuntimeException(e);
		}
	}
}
