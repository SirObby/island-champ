package net.sirobby.mods.islandchamp.Commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Commands.Commands.ChampCommand;
import net.sirobby.mods.islandchamp.Utils.Module;

public class CommandModule extends Module {

    @Override
    public void init(MinecraftClient mc) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            new ChampCommand().register_command(dispatcher);

        });

    }
}