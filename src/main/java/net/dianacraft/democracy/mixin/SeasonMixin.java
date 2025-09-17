package net.dianacraft.democracy.mixin;

import net.mat0u5.lifeseries.seasons.season.aprilfools.reallife.RealLife;
import net.mat0u5.lifeseries.seasons.season.thirdlife.ThirdLife;
import net.mat0u5.lifeseries.seasons.session.SessionAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dianacraft.democracy.gimmicks.GimmickManager.prepareVotes;
import static net.mat0u5.lifeseries.Main.currentSession;
import static net.mat0u5.lifeseries.Main.seasonConfig;
import static net.mat0u5.lifeseries.utils.other.OtherUtils.minutesToTicks;

@Mixin(RealLife.class)
public class SeasonMixin extends ThirdLife {
    public void addSessionActions(){
        currentSession.addSessionAction(new SessionAction(minutesToTicks(Integer.parseInt(seasonConfig.getProperty("vote_time"))) + minutesToTicks(Integer.parseInt(seasonConfig.getProperty("gimmick_frequency")))/2) {
            @Override
            public void trigger() {
                prepareVotes();
            }
        });
    }

	/*
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}

    @Inject(at = @At("TAIL"), method = "onPlayerFinishJoining")
    private void init(ServerPlayerEntity player, CallbackInfo ci) {

    }*/

}