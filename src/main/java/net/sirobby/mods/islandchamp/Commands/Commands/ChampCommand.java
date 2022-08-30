package net.sirobby.mods.islandchamp.Commands.Commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.sirobby.mods.islandchamp.Commands.Command;
import net.sirobby.mods.islandchamp.Configs.ClothConfig;
import net.sirobby.mods.islandchamp.IslandChamp;
import net.sirobby.mods.islandchamp.Utils.ChatUtil;

import java.io.IOException;

public class ChampCommand implements Command {

    @Override
    public void register_command(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        /*dispatcher.register(
                ClientCommandManager.literal("champ").executes(
                        ctx -> {
                            if (!IslandChamp.mod_enabled) {

                                ChatUtil.snedMessage("§aEnabling the mod Island Champ.");
                                IslandChamp.mod_enabled = true;
                                try {
                                    ClothConfig.saveConfigs();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                return 0;
                            }
                            else {
                                ChatUtil.snedMessage("§cDisabling the mod Island Champ.");
                                IslandChamp.mod_enabled = false;
                                try {
                                    ClothConfig.saveConfigs();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            return 0;
                        }
                )
        );*/
    }
}
