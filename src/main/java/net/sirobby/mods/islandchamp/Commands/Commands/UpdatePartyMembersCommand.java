package net.sirobby.mods.islandchamp.Commands.Commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.command.CommandManager;
import net.sirobby.mods.islandchamp.Commands.Command;
import net.sirobby.mods.islandchamp.IslandChamp;
import net.sirobby.mods.islandchamp.Utils.ChatUtil;


public class UpdatePartyMembersCommand implements Command {
    @Override
    public void register_command(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("updatepartymembers")
                        .then(ClientCommandManager.literal("add").then(
                                ClientCommandManager.argument("player", StringArgumentType.greedyString()).executes(
                                        ctx -> {
                                                IslandChamp.party_members.add(ctx.getArgument("player", String.class));
                                            return 0;
                                        }
                                )
                        )).then(
                                ClientCommandManager.literal("setall").then(
                                        ClientCommandManager.argument("first_player", StringArgumentType.greedyString()).then(
                                                ClientCommandManager.argument("second_player", StringArgumentType.greedyString()).then(
                                                        ClientCommandManager.argument("third_player", StringArgumentType.greedyString()).then(
                                                                ClientCommandManager.argument("fourth_player", StringArgumentType.greedyString()).executes(
                                                                        context -> {
                                                                            ChatUtil.sendMessage(String.format("Party members: %s %s %s %s", context.getArgument("first_player", String.class), context.getArgument("second_player", String.class), context.getArgument("third_player", String.class), context.getArgument("fourth_player", String.class)));
                                                                            if(IslandChamp.party_members.size() >= 1) IslandChamp.party_members.set(0, context.getArgument("first_player", String.class));
                                                                                else IslandChamp.party_members.add(context.getArgument("first_player", String.class));
                                                                            if(IslandChamp.party_members.size() >= 2) IslandChamp.party_members.set(1, context.getArgument("second_player", String.class));
                                                                                else IslandChamp.party_members.add(context.getArgument("second_player", String.class));
                                                                            if(IslandChamp.party_members.size() >= 3) IslandChamp.party_members.set(2, context.getArgument("third_player", String.class));
                                                                                else IslandChamp.party_members.add(context.getArgument("third_player", String.class));
                                                                            if(IslandChamp.party_members.size() >= 4) IslandChamp.party_members.set(3, context.getArgument("fourth_player", String.class));
                                                                                else IslandChamp.party_members.add(context.getArgument("fourth_player", String.class));
                                                                            return 0;
                                                                        }
                                                                )
                                                        ).executes(
                                                                context -> {
                                                                    ChatUtil.sendMessage(String.format("Party members: %s %s %s", context.getArgument("first_player", String.class), context.getArgument("second_player", String.class), context.getArgument("third_player", String.class)));
                                                                    if(IslandChamp.party_members.size() >= 1) IslandChamp.party_members.set(0, context.getArgument("first_player", String.class));
                                                                        else IslandChamp.party_members.add(context.getArgument("first_player", String.class));
                                                                    if(IslandChamp.party_members.size() >= 2) IslandChamp.party_members.set(1, context.getArgument("second_player", String.class));
                                                                        else IslandChamp.party_members.add(context.getArgument("second_player", String.class));
                                                                    if(IslandChamp.party_members.size() >= 3) IslandChamp.party_members.set(2, context.getArgument("third_player", String.class));
                                                                        else IslandChamp.party_members.add(context.getArgument("third_player", String.class));
                                                                    return 0;
                                                                }
                                                        )
                                                ).executes(
                                                        context -> {
                                                            ChatUtil.sendMessage(String.format("Party members: %s, %s", context.getArgument("first_player", String.class), context.getArgument("second_player", String.class)));
                                                            if(IslandChamp.party_members.size() >= 1) IslandChamp.party_members.set(0, context.getArgument("first_player", String.class));
                                                                else IslandChamp.party_members.add(context.getArgument("first_player", String.class));
                                                            if(IslandChamp.party_members.size() >= 2) IslandChamp.party_members.set(1, context.getArgument("second_player", String.class));
                                                                else IslandChamp.party_members.add(context.getArgument("second_player", String.class));
                                                            return 0;
                                                        }
                                                )
                                        ).executes(
                                                context -> {
                                                    ChatUtil.sendMessage(String.format("Party members: %s", context.getArgument("first_player", String.class)));
                                                    if(IslandChamp.party_members.size() >= 1) IslandChamp.party_members.set(0, context.getArgument("first_player", String.class));
                                                        else IslandChamp.party_members.add(context.getArgument("first_player", String.class));
                                                    return 0;
                                                }
                                        )
                                )
                        )
        );
    }
}
