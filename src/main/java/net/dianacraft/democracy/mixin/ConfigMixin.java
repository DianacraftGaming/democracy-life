package net.dianacraft.democracy.mixin;

import net.dianacraft.democracy.DemocracyLife;
import net.mat0u5.lifeseries.config.ConfigFileEntry;
import net.mat0u5.lifeseries.seasons.season.aprilfools.reallife.RealLifeConfig;
import net.mat0u5.lifeseries.seasons.season.thirdlife.ThirdLifeConfig;
import net.mat0u5.lifeseries.utils.enums.ConfigTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Mixin(RealLifeConfig.class)
public class ConfigMixin extends ThirdLifeConfig {
    private ConfigFileEntry<Boolean> RELIFE_MODE;
    private ConfigFileEntry<Boolean> AI_OBFUSCATION;

	@Inject(method = "<init>", at = @At("TAIL")) //this thing hates me
	private void constructor(CallbackInfo info) {
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"global", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        //this.RELIFE_MODE = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"global", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
    }
    /*
    @Inject(at = @At("TAIL"), method = "onPlayerFinishJoining")
    private void init(ServerPlayerEntity player, CallbackInfo ci) {

    }*/

}