package net.sirobby.mods.islandchamp.Keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.sirobby.mods.islandchamp.IslandChamp;
import net.sirobby.mods.islandchamp.Utils.Module;
import org.lwjgl.glfw.GLFW;

public class KeybindModule extends Module {

    @Override
    public void init(MinecraftClient mc) {

        //TODO: toggle chat(local or party) keybind but this requires to see if party chat is on or not.

        KeyBinding LeaveQueue; //the leavequeue command
        KeyBinding PartyCommand; //the party command
        KeyBinding FriendCommand; //the friendlist command

        LeaveQueue = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Leavequeue", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_X, // The keycode of the key
                "Island Champ" // The translation key of the keybinding's category.
        ));

        PartyCommand = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Party menu", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_O, // The keycode of the key
                "Island Champ" // The translation key of the keybinding's category.
        ));

        FriendCommand = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Friend menu", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_I, // The keycode of the key
                "Island Champ" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (LeaveQueue.wasPressed()) {
                assert client.player != null;
                if (IslandChamp.debugging_enabled) {
                    client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
                }
                client.player.sendCommand("leavequeue");
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (PartyCommand.wasPressed()) {
                assert client.player != null;
                if (IslandChamp.debugging_enabled) {
                    client.player.sendMessage(Text.literal("Key 2 was pressed!"), false);
                }
                client.player.sendCommand("party");
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (FriendCommand.wasPressed()) {
                assert client.player != null;
                if (IslandChamp.debugging_enabled) {
                    client.player.sendMessage(Text.literal("Key 3 was pressed!"), false);
                }
                client.player.sendCommand("fr");
            }
        });
    }
}
