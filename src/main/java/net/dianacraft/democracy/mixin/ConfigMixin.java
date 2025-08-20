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
    private ConfigFileEntry<Integer> GIMMICK_FREQUENCY;
    private ConfigFileEntry<Integer> GIMMICK_COUNT;

    public ConfigFileEntry<Object> GROUP_DEMOCRACY;

	@Inject(method = "<init>", at = @At("TAIL")) //this thing hates me
	private void constructor(CallbackInfo info) {
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"season.democracy", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        this.AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"season.democracy", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
        this.GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", getOrCreateInt("gimmick_frequency", 10),"season.democracy", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]");
        this.GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", getOrCreateInt("gimmick_count", 2),"season.democracy", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");

        this.GROUP_DEMOCRACY = new ConfigFileEntry("group_democracy", (Object)null, ConfigTypes.TEXT, "{season.democracy}", "Choose Your Life", "");

    }
    /*
    @Inject(at = @At("TAIL"), method = "onPlayerFinishJoining")
    private void init(ServerPlayerEntity player, CallbackInfo ci) {

    }*/

}