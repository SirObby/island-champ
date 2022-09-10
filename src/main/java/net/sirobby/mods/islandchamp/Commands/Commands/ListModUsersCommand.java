package net.sirobby.mods.islandchamp.Commands.Commands;

import net.sirobby.mods.islandchamp.Commands.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.sirobby.mods.islandchamp.Utils.ChatUtil;

import static net.sirobby.mods.islandchamp.Websocket.SocketServer.users;

public class ListModUsersCommand implements Command{
    @Override
    public void register_command(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("ICusers"));

    }
}
