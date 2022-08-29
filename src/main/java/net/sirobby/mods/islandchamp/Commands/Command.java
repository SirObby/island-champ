package net.sirobby.mods.islandchamp.Commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public interface Command {
    void register_command(CommandDispatcher<FabricClientCommandSource> dispatcher);
}
