package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionAction;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.mat0u5.lifeseries.Main.*;
import static net.mat0u5.lifeseries.utils.other.OtherUtils.minutesToTicks;
import static net.mat0u5.lifeseries.utils.other.OtherUtils.secondsToTicks;

public class GetCake extends Gimmick {
    public GetCake(){
        voteText = "Everyone gets Cake!";
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void activate() {
        if (Integer.parseInt(seasonConfig.getProperty("cake_waittime")) > 0) {
            for (ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()) {
                player.sendMessage(Text.of("Cake incoming in " + seasonConfig.getProperty("cake_waittime") + " seconds!"));
            }
        }
        currentSession.addSessionAction(new SessionAction(currentSession.getPassedTime() + secondsToTicks(Integer.parseInt(seasonConfig.getProperty("cake_waittime")))) {
            @Override
            public void trigger() {
                for (ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()){
                    player.setFireTicks(100);
                    player.sendMessage(Text.of("§cThe Cake is a lie."));
                    DemocracyLife.LOGGER.info("[Gimmick] The Cake is a lie.");
                    SessionTranscript.addMessageWithTime("[Gimmick] The Cake is a lie.");
                }
            }
        });

    }
}
