package net.dianacraft.democracy.mixin;

import net.mat0u5.lifeseries.seasons.season.aprilfools.reallife.RealLife;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RealLife.class)
public class SeasonMixin {
	/*
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}

    @Inject(at = @At("TAIL"), method = "onPlayerFinishJoining")
    private void init(ServerPlayerEntity player, CallbackInfo ci) {

    }*/

}