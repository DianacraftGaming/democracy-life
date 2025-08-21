package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.livesManager;

public class MakeBoogeyman extends Gimmick {
    public ServerPlayerEntity player;
    public String voteText = "Turn a random non-red player into a Boogeyman";
    private boolean revealBoogeyman = false;

    public MakeBoogeyman(){
        player = randomisePlayer();
        if (currentSeason.getConfig().getProperty("reveal_boogeyman").equalsIgnoreCase("true"))
            revealBoogeyman = true;
        if (player != null && revealBoogeyman){
            voteText = "Turn " + player.getNameForScoreboard() + " into a Boogeyman.";
        }
    }

    public static boolean isAvailable(){
        List<ServerPlayerEntity> nonRedPlayers = livesManager.getNonRedPlayers();
        return !nonRedPlayers.isEmpty();
    }

    private ServerPlayerEntity randomisePlayer(){
        List<ServerPlayerEntity> nonRedPlayers = livesManager.getNonRedPlayers();
        if (!nonRedPlayers.isEmpty()) {
            Collections.shuffle(nonRedPlayers);
            return nonRedPlayers.getFirst();
        }
        return null;
    }

    public void activate() {
        if (player == null){
            if (!isAvailable()) return;
            player = randomisePlayer();
        }
        currentSeason.boogeymanManager.addBoogeyman(player);
        DemocracyLife.LOGGER.info("[Gimmick] Made " + player.getNameForScoreboard() + " into a Boogeyman.");
        SessionTranscript.addMessageWithTime("[Gimmick] Made " + player.getNameForScoreboard() + " into a Boogeyman.");
    }
    public void deactivate() {}
}
