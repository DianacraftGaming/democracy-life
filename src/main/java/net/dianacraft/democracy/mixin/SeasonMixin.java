package net.dianacraft.democracy.mixin;

import net.dianacraft.democracy.gimmicks.Gimmicks;
import net.mat0u5.lifeseries.seasons.season.aprilfools.reallife.RealLife;
import net.mat0u5.lifeseries.seasons.season.thirdlife.ThirdLife;
import net.mat0u5.lifeseries.seasons.session.SessionAction;
import net.mat0u5.lifeseries.utils.player.TeamUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static net.dianacraft.democracy.gimmicks.GimmickManager.activeGimmicks;
import static net.dianacraft.democracy.gimmicks.GimmickManager.prepareVotes;
import static net.mat0u5.lifeseries.Main.*;
import static net.mat0u5.lifeseries.utils.other.OtherUtils.minutesToTicks;

@Mixin(RealLife.class)
public class SeasonMixin extends ThirdLife {

    public String getAdminCommands() {
        return "/lifeseries, /session, /claimkill, /lives, /gimmick, /vote";
    }
    public String getNonAdminCommands() {
        return "/claimkill, /lives, /vote";
    }

    @Override
    public void createTeams() {
        super.createTeams();
        TeamUtils.createTeam("golden", "Golden", Formatting.GOLD);
    }

    public void addSessionActions(){
        currentSession.addSessionAction(new SessionAction(minutesToTicks(Integer.parseInt(seasonConfig.getProperty("vote_time"))) + minutesToTicks(Integer.parseInt(seasonConfig.getProperty("gimmick_frequency")))/2) {
            @Override
            public void trigger() {
                prepareVotes(true);
            }
        });
    }

    public boolean isAllowedToAttack(ServerPlayerEntity attacker, ServerPlayerEntity victim, boolean allowSelfDefense) {
        if (this.livesManager.isOnLastLife(attacker, false)) {
            return true;
        } else if (this.boogeymanManager.isBoogeymanThatCanBeCured(attacker, victim)) {
            return true;
        } else if (Objects.equals(TeamUtils.getPlayerTeam(victim).getName(), "golden")) {
            return true;
        } else {
            return allowSelfDefense && attacker.getPrimeAdversary() == victim && this.isAllowedToAttack(victim, attacker, false);
        }
    }

    @Override
    public void onPlayerKilledByPlayer(ServerPlayerEntity victim, ServerPlayerEntity killer) {
        super.onPlayerKilledByPlayer(victim, killer);
        if (Objects.equals(TeamUtils.getPlayerTeam(victim).getName(), "golden")){
            livesManager.addPlayerLife(killer);
            currentSeason.reloadPlayerTeam(victim);
            activeGimmicks.get(Gimmicks.MAKE_GOLDEN).deactivate();
        }
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