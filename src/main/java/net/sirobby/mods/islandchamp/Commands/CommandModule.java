package net.sirobby.mods.islandchamp.Commands;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.sirobby.mods.islandchamp.Commands.Commands.ListModUsersCommand;
import net.sirobby.mods.islandchamp.Commands.Commands.UpdatePartyMembersCommand;
import net.sirobby.mods.islandchamp.Utils.Module;

public class CommandModule extends Module {

    @Override
    public void init(MinecraftClient mc) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

            new UpdatePartyMembersCommand().register_command(dispatcher);
            new ListModUsersCommand().register_command(dispatcher);
        });

    }
}