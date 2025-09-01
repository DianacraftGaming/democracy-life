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
    private ConfigFileEntry<Boolean> VOTE_RECAST;

    private ConfigFileEntry<Boolean> AI_OBFUSCATION;
    private ConfigFileEntry<Boolean> REVEAL_BOOGEYMAN;
    private ConfigFileEntry<Boolean> REVEAL_PLAYERS;

    public ConfigFileEntry<Object> GROUP_DEMOCRACY;
    public ConfigFileEntry<Object> GROUP_DEMOCRACY_GIMMICKS;
    public ConfigFileEntry<Object> GROUP_DEMOCRACY_GIMMICKS_EXTENDED;


    /*
    private final ConfigFileEntry<Boolean> RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"season.democracy", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
    private final ConfigFileEntry<Integer> GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", getOrCreateInt("gimmick_frequency", 10),"season.democracy", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]");
    private final ConfigFileEntry<Integer> GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", getOrCreateInt("gimmick_count", 2),"season.democracy", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");

    private final ConfigFileEntry<Boolean> AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"season.democracygimmicks", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
    private final ConfigFileEntry<Boolean> REVEAL_BOOGEYMAN = new ConfigFileEntry("reveal_boogeyman", getOrCreateBoolean("reveal_boogeyman", false),"season.democracygimmicks", "Reveal Boogeyman", "Whether \"Turn a random player into a boogeyman\" gimmick should specify the name of the chosen player.");
    private final ConfigFileEntry<Boolean> REVEAL_PLAYERS = new ConfigFileEntry("reveal_players", getOrCreateBoolean("reveal_players", true),"season.democracygimmicks", "Reveal Players", "Whether gimmicks that use a random player should specify the name of the chosen player.");

    public final ConfigFileEntry<Object> GROUP_DEMOCRACY = new ConfigFileEntry("group_democracy", (Object)null, ConfigTypes.TEXT, "{season.democracy}", "Choose Your Life [General]", "");
    public final ConfigFileEntry<Object> GROUP_DEMOCRACY_GIMMICKS = new ConfigFileEntry("group_democracygimmicks", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Choose Your Life [Gimmicks]", "");
    */

    /*
	@Inject(method = "<init>", at = @At("TAIL")) //this thing hates me
	private void constructor(CallbackInfo info) {
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"season.democracy", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        this.GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", getOrCreateInt("gimmick_frequency", 10),"season.democracy", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]");
        this.GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", getOrCreateInt("gimmick_count", 2),"season.democracy", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");

        this.AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"season.democracygimmicks", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
        this.REVEAL_BOOGEYMAN = new ConfigFileEntry("reveal_boogeyman", getOrCreateBoolean("reveal_boogeyman", false),"season.democracygimmicks", "Reveal Boogeyman", "Whether \"Turn a random player into a boogeyman\" gimmick should specify the name of the chosen player.");
        this.REVEAL_PLAYERS = new ConfigFileEntry("reveal_players", getOrCreateBoolean("reveal_players", true),"season.democracygimmicks", "Reveal Players", "Whether gimmicks that use a random player should specify the name of the chosen player.");

        this.GROUP_DEMOCRACY = new ConfigFileEntry("group_democracy", (Object)null, ConfigTypes.TEXT, "{season.democracy}", "Choose Your Life [General]", "");
        this.GROUP_DEMOCRACY_GIMMICKS = new ConfigFileEntry("group_democracygimmicks", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Choose Your Life [Gimmicks]", "");

        this.BOOGEYMAN.defaultValue = true;


        /*
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"general", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        this.GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", getOrCreateInt("gimmick_frequency", 10),"general", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]");
        this.GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", getOrCreateInt("gimmick_count", 2),"general", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");

        this.AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"general", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
        this.REVEAL_BOOGEYMAN = new ConfigFileEntry("reveal_boogeyman", getOrCreateBoolean("reveal_boogeyman", false),"general", "Reveal Boogeyman", "Whether \"Turn a random player into a boogeyman\" gimmick should specify the name of the chosen player.");
        this.REVEAL_PLAYERS = new ConfigFileEntry("reveal_players", getOrCreateBoolean("reveal_players", true),"general", "Reveal Players", "Whether gimmicks that use a random player should specify the name of the chosen player.");

    }*/

    private void initialiseEntries(){
        this.RELIFE_MODE = new ConfigFileEntry("relife_mode", getOrCreateBoolean("relife_mode", false),"season.democracy", "ReLife Mode", "Changes some randomised player gimmicks into preset players from ReLife Series. If none of the ReLifers are present, disables those gimmicks from activating.");
        this.GIMMICK_FREQUENCY = new ConfigFileEntry("gimmick_frequency", getOrCreateInt("gimmick_frequency", 10),"season.democracy", "Gimmick Frequency", "How often Gimmick polls should get activated [in minutes]");
        this.GIMMICK_COUNT = new ConfigFileEntry("gimmick_count", getOrCreateInt("gimmick_count", 2),"season.democracy", "Gimmick Count", "How many Gimmicks appear as a vote option in a poll. Minimal value is 1.");
        this.VOTE_RECAST = new ConfigFileEntry("vote_recast", getOrCreateBoolean("vote_recast", false),"season.democracy", "Vote Recasting", "Whether players should be able to recast their votes.");

        this.AI_OBFUSCATION = new ConfigFileEntry("ai_obfuscation", getOrCreateBoolean("ai_obfuscation", true),"season.democracygimmicks", "Ai Obfuscation", "Whether AIs that replace players should have a robotic voice overlay");
        this.REVEAL_BOOGEYMAN = new ConfigFileEntry("reveal_boogeyman", getOrCreateBoolean("reveal_boogeyman", false),"season.democracygimmicks", "Reveal Boogeyman", "Whether \"Turn a random player into a boogeyman\" gimmick should specify the name of the chosen player.");
        this.REVEAL_PLAYERS = new ConfigFileEntry("reveal_players", getOrCreateBoolean("reveal_players", true),"season.democracygimmicks", "Reveal Players", "Whether gimmicks that use a random player should specify the name of the chosen player.");

        this.GROUP_DEMOCRACY = new ConfigFileEntry("group_democracy", (Object)null, ConfigTypes.TEXT, "{season.democracy}", "Choose Your Life [General]", "");
        this.GROUP_DEMOCRACY_GIMMICKS = new ConfigFileEntry("group_democracygimmicks", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Choose Your Life [Gimmicks]", "");
        this.GROUP_DEMOCRACY_GIMMICKS_EXTENDED = new ConfigFileEntry("group_democracygimmicksextended", (Object)null, ConfigTypes.TEXT, "{season.democracygimmicks}", "Extended Gimmick Settings", "");

        this.BOOGEYMAN.defaultValue = true;
    }

    protected List<ConfigFileEntry<?>> getSeasonSpecificConfigEntries() {
        initialiseEntries();
        /*
        List<ConfigFileEntry<?>> list = new ArrayList<>();
        list.add(this.GROUP_DEMOCRACY);
        list.add(this.GROUP_DEMOCRACY_GIMMICKS);
        list.add(this.RELIFE_MODE);
        list.add(this.GIMMICK_COUNT);
        list.add(this.GIMMICK_FREQUENCY);
        list.add(this.AI_OBFUSCATION);
        list.add(this.REVEAL_BOOGEYMAN);
        list.add(this.REVEAL_PLAYERS);

        return list;*/
        return new ArrayList(List.of(this.GROUP_DEMOCRACY, this.GROUP_DEMOCRACY_GIMMICKS, this.RELIFE_MODE, this.GIMMICK_COUNT, this.GIMMICK_FREQUENCY, this.AI_OBFUSCATION, this.REVEAL_BOOGEYMAN, this.REVEAL_PLAYERS));
    }

}