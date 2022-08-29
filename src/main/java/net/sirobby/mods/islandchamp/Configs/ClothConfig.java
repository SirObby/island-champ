package net.sirobby.mods.islandchamp.Configs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.sirobby.mods.islandchamp.IslandChamp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Map;

public class ClothConfig {

    public static Screen build_screen() {
        MinecraftClient mc = MinecraftClient.getInstance();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(mc.currentScreen)
                .setTitle(Text.translatable("title.islandchamp.config"))
                .setSavingRunnable(
                        () -> {
                            try {
                                saveConfigs();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.islandchamp.general"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //boolean currentValue = true;

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.islandchamp.enabled"), IslandChamp.mod_enabled)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.islandchamp.enabled")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.mod_enabled = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        Screen screen = builder.build();

        //MinecraftClient.getInstance().setScreen(screen);
        return screen;
    }

    public static void saveConfigs() throws IOException {

        Path p = FabricLoader.getInstance().getConfigDir().resolve("islandchamp.json");

        //String c = Files.readString(p);

        JsonObject object = new Gson().fromJson(String.format("{ \"enabled\": %s}", IslandChamp.mod_enabled ), JsonObject.class);

        Files.writeString(p, object.toString());

    } // Loading is done from IslandChamp.java

}
