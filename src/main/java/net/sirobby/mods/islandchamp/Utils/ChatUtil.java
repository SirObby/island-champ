package net.sirobby.mods.islandchamp.Utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtil {
    public static void snedMessage(String message) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(message), false);
    }
}
