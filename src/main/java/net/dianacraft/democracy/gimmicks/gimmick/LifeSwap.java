package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.livesManager;
import static net.mat0u5.lifeseries.Main.seasonConfig;

public class LifeSwap extends Gimmick {
    ServerPlayerEntity player1;
    ServerPlayerEntity player2;
    boolean revealPlayers = false;

    @Override
    public boolean isAvailable() {
        return livesManager.getAlivePlayers().size() >= 2;
    }

    private void randomisePlayers(){
        if (livesManager.getAlivePlayers().size() >= 2) {
            List<ServerPlayerEntity> players = livesManager.getAlivePlayers();
            Collections.shuffle(players);
            player1 = players.get(0);
            player2 = players.get(1);
        }
    }

    public LifeSwap(){
        randomisePlayers();
        if (seasonConfig.getProperty("reveal_players").equalsIgnoreCase("true"))
            revealPlayers = true;
        if (player1 != null && player2 != null && revealPlayers){
            voteText = player1.getNameForScoreboard() + " and " + player2.getNameForScoreboard() + " switch life amounts";
        } else {
            voteText = "Two random players switch life amounts";
        }
    }

    @Override
    public void activate() {
        if (player1 != null && player2 != null){
            int tmp = livesManager.getPlayerLives(player1);
            livesManager.setPlayerLives(player1, livesManager.getPlayerLives(player2));
            livesManager.setPlayerLives(player2, tmp);
            DemocracyLife.LOGGER.info("[Gimmick] Swapped the lives of " + player1.getNameForScoreboard() + " and " + player2.getNameForScoreboard());
            SessionTranscript.addMessageWithTime("[Gimmick] Swapped the lives of " + player1.getNameForScoreboard() + " and " + player2.getNameForScoreboard());
        }
    }
}
