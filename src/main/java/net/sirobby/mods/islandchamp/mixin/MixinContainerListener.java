package net.sirobby.mods.islandchamp.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GenericContainerScreenHandler.class)
public class MixinContainerListener {

    @Shadow
    @Final
    private Inventory inventory;
    @Shadow
    @Final
    private int rows;



}
