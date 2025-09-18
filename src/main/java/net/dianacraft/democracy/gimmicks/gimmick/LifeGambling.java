package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;

public class LifeGambling extends Gimmick {
    public String voteText = "Everyone gets a 40/60 chance to either gain or lose a life";

    public static boolean isAvailable(){
        return true;
    }

    public void activate() {
        List<ServerPlayerEntity> activePlayers = currentSeason.livesManager.getAlivePlayers();

        for (ServerPlayerEntity player : activePlayers){
            if (Math.random() >= 0.4){
                currentSeason.livesManager.removePlayerLife(player);
                DemocracyLife.LOGGER.info("[Gimmick] " + player.getNameForScoreboard() + " lost a life.");
                SessionTranscript.addMessageWithTime("[Gimmick] " + player.getNameForScoreboard() + " lost a life.");
            } else {
                currentSeason.livesManager.addPlayerLife(player);
                DemocracyLife.LOGGER.info("[Gimmick] " + player.getNameForScoreboard() + " gained a life.");
                SessionTranscript.addMessageWithTime("[Gimmick] " + player.getNameForScoreboard() + " gained a life.");
            }
        }
        //DemocracyLife.LOGGER.info("Activated a Gimmick");
        //SessionTranscript.addMessageWithTime("Activated a Gimmick");
    }
}
