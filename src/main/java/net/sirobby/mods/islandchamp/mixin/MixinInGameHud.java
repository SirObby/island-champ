package net.sirobby.mods.islandchamp.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    public void renderHUD() {

    }

}
