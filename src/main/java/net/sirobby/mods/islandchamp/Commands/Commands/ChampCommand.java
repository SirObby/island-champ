package net.sirobby.mods.islandchamp.Commands.Commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.sirobby.mods.islandchamp.Commands.Command;

public class ChampCommand implements Command {

    @Override
    public void register_command(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("champ").executes(
                        ctx -> {


                            return 0;
                        }
                )
        );
    }
}
