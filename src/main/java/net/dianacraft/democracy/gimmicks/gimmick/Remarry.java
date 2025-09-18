package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.seasonConfig;

public class Remarry extends Gimmick {
    public boolean isAvailable(){
        if (seasonConfig.getProperty("relife_mode").equalsIgnoreCase("false")) {
            return false;
        } else {
            List<String> usernames = new ArrayList<>();
            for (ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()) {
                usernames.add(player.getNameForScoreboard());
            }
            return (usernames.contains("wormsxp") && usernames.contains("Aleoryz"));
        }
    }

    public Remarry(){
        voteText = "Aleoryz gets teleported to wormsxp";
    }

    @Override
    public void activate() {
        if (!isAvailable()) return;

        ServerPlayerEntity al =  PlayerUtils.getPlayer("Aleoryz");
        ServerPlayerEntity worm =  PlayerUtils.getPlayer("wormsxp");

        al.requestTeleport(worm.getX(), worm.getY(), worm.getZ());
        DemocracyLife.LOGGER.info("[Gimmick] Aleoryz was teleported to wormsxp");
        SessionTranscript.addMessageWithTime("[Gimmick] Aleoryz was teleported to wormsxp");
    }
}
