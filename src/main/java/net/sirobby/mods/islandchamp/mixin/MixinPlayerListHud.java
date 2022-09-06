package net.sirobby.mods.islandchamp.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.world.GameMode;
import net.sirobby.mods.islandchamp.Websocket.SocketServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListHud.class)
public class MixinPlayerListHud {

    @Inject(at = @At("HEAD"), method = "getPlayerName", cancellable = true)
    public void getPlayerName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        //Text name = spectatorFormat(entry, Team.decorateName(entry.getScoreboardTeam(), Text.of(new String(entry.getProfile().getName()))));
        if(entry.getDisplayName() != null) {
            if(entry.getDisplayName().getString().length() > 3) {
                //System.out.println(entry.getDisplayName().getString().substring(3));
                if (SocketServer.hasUser(entry.getDisplayName().getString().substring(3))) {
                    Style s = Style.EMPTY;
                    s = s.withColor(TextColor.fromRgb(1481885));
                    cir.setReturnValue(entry.getDisplayName().copy().append(Text.of("★").copy().setStyle(s)));
                }
                // If you're a contributor, you can change add your name.
                if(entry.getDisplayName().getString().substring(3).equals("SirObby_") || entry.getDisplayName().getString().substring(3).equals("DarkCheese_")) {
                    Style s = Style.EMPTY;
                    s = s.withColor(TextColor.fromRgb(11141290));
                    cir.setReturnValue(entry.getDisplayName().copy().append(Text.of("★").copy().setStyle(s)));
                }
            }
        }
    }

    private Text spectatorFormat(PlayerListEntry playerListEntry, MutableText decorateName) {
        return playerListEntry.getGameMode() == GameMode.SPECTATOR ? decorateName.formatted(Formatting.ITALIC) : decorateName;
    }

}
