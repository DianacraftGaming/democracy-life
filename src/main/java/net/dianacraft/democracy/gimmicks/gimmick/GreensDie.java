package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.mat0u5.lifeseries.Main.currentSeason;

public class GreensDie extends Gimmick {
    public GreensDie(){
        voteText = "All Green and Dark Green players die";
    }

    @Override
    public boolean isAvailable() {
        for(ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()) {
            if (currentSeason.livesManager.isOnAtLeastLives(player, 3, false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void activate() {
        for(ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()) {
            if (currentSeason.livesManager.isOnAtLeastLives(player, 3, false)) {
                player.kill(player.getWorld());
                DemocracyLife.LOGGER.info("[Gimmick] Killed " + player.getNameForScoreboard());
                SessionTranscript.addMessageWithTime("[Gimmick] Killed " + player.getNameForScoreboard());
            }
        }
    }
}
