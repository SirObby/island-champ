package net.sirobby.mods.islandchamp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Commands.CommandModule;
import net.sirobby.mods.islandchamp.Configs.ClothConfig;
import net.sirobby.mods.islandchamp.Keybinds.KeybindModule;
import net.sirobby.mods.islandchamp.Websocket.WebsocketModule;
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

	public static final int version = 1;
	// This is used to ensure that the config loader does not go out of bounds

	// Settings
	public static boolean debugging_enabled = false; // Automatic disable.
	public static boolean sidechat_enabled = true; // Automatic enable.

	public static int sidechat_x = 310; // test?
	public static int sidechat_w = 90;

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		MinecraftClient mc = MinecraftClient.getInstance();

		new CommandModule().init(mc);
		// new WebsocketModule().init(mc);
		// Sockets crash real client for some reason
		new KeybindModule().init(mc);

		try {
			Path p = FabricLoader.getInstance().getConfigDir().resolve("islandchamp.json");

			String c = Files.readString(p);

			JsonObject jsonObject = new Gson().fromJson(c, JsonObject.class);

			if(!Files.exists(p)){
				// Set default settings

				debugging_enabled = true;

				ClothConfig.saveConfigs();
			} else {
				if(jsonObject.has("v")) {
					switch (jsonObject.get("v").getAsInt()) {
						case 1: {
							debugging_enabled = jsonObject.get("debugging_enabled").getAsBoolean();
							sidechat_enabled = jsonObject.get("sidechat").getAsBoolean();
							sidechat_x = jsonObject.get("sidechat_x").getAsInt();
							sidechat_w = jsonObject.get("sidechat_w").getAsInt();
						}
						case 2: {
							debugging_enabled = jsonObject.get("debugging_enabled").getAsBoolean();
							sidechat_enabled = jsonObject.get("sidechat").getAsBoolean();
							sidechat_x = jsonObject.get("sidechat_x").getAsInt();
							sidechat_w = jsonObject.get("sidechat_w").getAsInt();

							// Add websocket checks.
						}
					}
				} else {
					System.out.printf("%s is out of date.%n", p);
					debugging_enabled = jsonObject.get("debugging_enabled").getAsBoolean();
					sidechat_enabled = jsonObject.get("sidechat").getAsBoolean();
					sidechat_x = jsonObject.get("sidechat_x").getAsInt();
					sidechat_w = jsonObject.get("sidechat_w").getAsInt();
				}
			}

		} catch (IOException e) {
			//throw new RuntimeException(e);
		}
	}
}
