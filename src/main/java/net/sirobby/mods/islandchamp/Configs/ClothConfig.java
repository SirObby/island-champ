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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        ConfigCategory sidechat = builder.getOrCreateCategory(Text.translatable("category.islandchamp.sidechat"));
        ConfigCategory advanced = builder.getOrCreateCategory(Text.translatable("category.islandchamp.advanced"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        //boolean currentValue = true;

        general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.sidechat.enabled"), IslandChamp.sidechat_enabled)
                .setDefaultValue(true) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.sidechat.enabled")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.sidechat_enabled = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        advanced.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.debugging.enabled"), IslandChamp.debugging_enabled)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.debugging.enabled")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.debugging_enabled = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        /*advanced.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.configurator.enabled"), IslandChamp.debugging_enabled)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.configurator.enabled")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.debugging_enabled = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config*/

        sidechat.addEntry(entryBuilder.startIntSlider(Text.translatable("option.sidechatx.int"), IslandChamp.sidechat_x, 1, 1920)
                .setDefaultValue(310) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.sidechatx.int")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.sidechat_x = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        sidechat.addEntry(entryBuilder.startIntSlider(Text.translatable("option.sidechatw.int"), IslandChamp.sidechat_w, 1, 1920)
                .setDefaultValue(90) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.sidechatw.int")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.sidechat_w = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        sidechat.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.sidechat.party.enabled"), IslandChamp.sidechat_party_enabled)
                .setDefaultValue(false) // Recommended: Used when user click "Reset"
                .setTooltip(Text.translatable("tooltip.sidechat.party.enabled")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> IslandChamp.sidechat_party_enabled = newValue) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

        //MinecraftClient.getInstance().setScreen(screen);
        return builder.build();
    }

    public static void saveConfigs() throws IOException {

        Path p = FabricLoader.getInstance().getConfigDir().resolve("islandchamp.json");

        //String c = Files.readString(p);

        JsonObject object = new Gson().fromJson(String.format("{ \"v\": %d, \"debugging_enabled\": %s, \"sidechat\": %s, \"sidechat_x\": %d, \"sidechat_w\": %d, \"sidechat_party\": %s }", IslandChamp.version, IslandChamp.debugging_enabled,  IslandChamp.sidechat_enabled, IslandChamp.sidechat_x, IslandChamp.sidechat_w, IslandChamp.sidechat_party_enabled), JsonObject.class);

        Files.writeString(p, object.toString());

    } // Loading is done from IslandChamp.java

    public static void defaultConfigs() throws IOException {
        Path p = FabricLoader.getInstance().getConfigDir().resolve("islandchamp.json");

        JsonObject object = new Gson().fromJson(String.format("{ \"v\": %d, \"debugging_enabled\": %s \"sidechat\": %s, \"sidechat_x\": %d, \"sidechat_w\": %d, \"sidechat_party\": %s }", IslandChamp.version, false, true, 310, 90, false ), JsonObject.class);

        Files.writeString(p, object.toString());
    }

}
