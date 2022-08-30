package net.sirobby.mods.islandchamp.mixin;

import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public class MixinBossBarListener {

    @Inject(method={"renderBossBar(Lnet/minecraft/client/util/math/MatrixStack;IILnet/minecraft/entity/boss/BossBar;II)V"}, at = @At("HEAD"))
    private void renderBossBar(MatrixStack matrices, int x, int y, BossBar bossBar, int width, int height, CallbackInfo ci) {
        
    }

}
