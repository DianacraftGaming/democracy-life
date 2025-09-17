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

import java.util.ArrayList;
import java.util.List;

@Mixin(RealLifeConfig.class)
public class ConfigMixin extends ThirdLifeConfig {

    private ConfigFileEntry<Boolean> RELIFE_MODE;;
    private ConfigFileEntry<Integer> GIMMICK_FREQUENCY;
    private ConfigFileEntry<Integer> GIMMICK_COUNT;
    private ConfigFileEntry<Integer> VOTE_TIME;
    private ConfigFileEntry<Boolean> VOTE_RECAST;

    private ConfigFileEntry<Boolean> AI_OBFUSCATION;
    private ConfigFileEntry<Boolean> REVEAL_BOOGEYMAN;
    private ConfigFileEntry<Boolean> REVEAL_PLAYERS;

    public ConfigFileEntry<Object> GROUP_DEMOCRACY;
    public ConfigFileEntry<Object> GROUP_DEMOCRACY_GIMMICKS;
    public ConfigFileEntry<Object> GROUP_DEMOCRACY_GIMMICKS_EXTENDED;

    private void initialiseEntries(){
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", false,"season.democracy", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        this.GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", 9,"season.democracy", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]. Does not include the voting time");
        this.VOTE_TIME = new ConfigFileEntry("vote_time", 1,"season.democracy", "Voting Time", "How long a Gimmick poll lasts [in minutes]");
        this.GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", 2,"season.democracy", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");
        this.VOTE_RECAST = new ConfigFileEntry("vote_recast", false,"season.democracy", "Vote Recasting", "Whether players should be able to recast their votes.");

        this.AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", true,"season.democracygimmicks", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
        this.REVEAL_BOOGEYMAN = new ConfigFileEntry("reveal_boogeyman", false,"season.democracygimmicks", "Reveal Boogeyman", "Whether \"Turn a random player into a boogeyman\" gimmick should specify the name of the chosen player.");
        this.REVEAL_PLAYERS = new ConfigFileEntry("reveal_players", true,"season.democracygimmicks", "Reveal Players", "Whether gimmicks that use a random player should specify the name of the chosen player.");

        this.GROUP_DEMOCRACY = new ConfigFileEntry("group_democracy", (Object)null, ConfigTypes.TEXT, "{season.democracy}", "Choose Your Life [General]", "");
        this.GROUP_DEMOCRACY_GIMMICKS = new ConfigFileEntry("group_democracygimmicks", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Choose Your Life [Gimmicks]", "");
        this.GROUP_DEMOCRACY_GIMMICKS_EXTENDED = new ConfigFileEntry("group_democracygimmicksextended", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Extended Gimmick Settings", "");
    }

    public void instantiateProperties(){
        this.BOOGEYMAN.defaultValue = true;
        this.DEFAULT_LIVES.defaultValue = 6;
        super.instantiateProperties();
    }

    protected List<ConfigFileEntry<?>> getSeasonSpecificConfigEntries() {
        initialiseEntries();
        return new ArrayList(List.of(this.GROUP_DEMOCRACY, this.GROUP_DEMOCRACY_GIMMICKS, this.RELIFE_MODE, this.GIMMICK_COUNT, this.GIMMICK_FREQUENCY, this.VOTE_TIME, this.VOTE_RECAST, this.AI_OBFUSCATION, this.REVEAL_BOOGEYMAN, this.REVEAL_PLAYERS));
    }
}