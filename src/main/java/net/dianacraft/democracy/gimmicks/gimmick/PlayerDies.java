package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.livesManager;

public class PlayerDies extends Gimmick {
    public ServerPlayerEntity player;
    private boolean revealPlayers = true;

    public static boolean isAvailable(){
        return !getAvailablePlayers().isEmpty();
    }

    private static List<ServerPlayerEntity> getAvailablePlayers(){
        List<ServerPlayerEntity> players = new ArrayList<>();
        if(currentSeason.getConfig().getProperty("relife_mode").equalsIgnoreCase("true")){
            List<ServerPlayerEntity> nonRedPlayers = livesManager.getNonRedPlayers();
            for (ServerPlayerEntity nonred : nonRedPlayers){
                if (nonred.getNameForScoreboard().equalsIgnoreCase("wormsxp") || nonred.getNameForScoreboard().equalsIgnoreCase("carlbot") || nonred.getNameForScoreboard().equalsIgnoreCase("aleoryz")){
                    players.add(nonred);
                }
            }
        } else {
            players = livesManager.getNonRedPlayers();
        }
        return players;
    }

    private ServerPlayerEntity randomisePlayer(){
        List<ServerPlayerEntity> nonRedPlayers = getAvailablePlayers();
        if (!nonRedPlayers.isEmpty()) {
            Collections.shuffle(nonRedPlayers);
            return nonRedPlayers.getFirst();
        }
        return null;
    }

    public PlayerDies(){
        player = randomisePlayer();
        if (currentSeason.getConfig().getProperty("reveal_players").equalsIgnoreCase("true"))
            revealPlayers = true;
        if (player != null && revealPlayers){
            voteText = player.getNameForScoreboard() + " dies.";
        } else {
            voteText = "A random non-red player dies.";
        }
    }

    public void activate() {
        if (player == null){
            if (!isAvailable()) return;
            player = randomisePlayer();
        }
        player.kill(player.getWorld());
        DemocracyLife.LOGGER.info("[Gimmick] Killed " + player.getNameForScoreboard());
        SessionTranscript.addMessageWithTime("[Gimmick] Killed " + player.getNameForScoreboard());
    }
    public void deactivate() {}
}
