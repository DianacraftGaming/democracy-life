package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.seasonConfig;

public class LifeGambling extends Gimmick {

    private int winChance = 40;

    public LifeGambling(){
        winChance = Integer.parseInt(seasonConfig.getProperty("gambling_winchance"));
        winChance = Math.min(winChance, 99);
        winChance = Math.max(winChance, 1);
        int loseChance = 100 - winChance;

        voteText = "Everyone gets a "+winChance+"/"+loseChance+" chance to either gain or lose a life";
    }

    public boolean isAvailable(){
        return !currentSeason.livesManager.getAlivePlayers().isEmpty();
    }

    public void activate() {
        List<ServerPlayerEntity> activePlayers = currentSeason.livesManager.getAlivePlayers();

        for (ServerPlayerEntity player : activePlayers){
            if (Math.random() >= (double) winChance / 100){
                currentSeason.livesManager.removePlayerLife(player);
                DemocracyLife.LOGGER.info("[Gimmick] " + player.getNameForScoreboard() + " lost a life.");
                SessionTranscript.addMessageWithTime("[Gimmick] " + player.getNameForScoreboard() + " lost a life.");
                PlayerUtils.sendTitle(player, Text.literal("-1 life").formatted(Formatting.RED), 10, 20, 10);
                PlayerUtils.playSoundToPlayer(player, SoundEvents.ENTITY_PLAYER_HURT);
            } else {
                currentSeason.livesManager.addPlayerLife(player);
                DemocracyLife.LOGGER.info("[Gimmick] " + player.getNameForScoreboard() + " gained a life.");
                SessionTranscript.addMessageWithTime("[Gimmick] " + player.getNameForScoreboard() + " gained a life.");
                PlayerUtils.sendTitle(player, Text.literal("+1 life").formatted(Formatting.GREEN), 10, 20, 10);
                PlayerUtils.playSoundToPlayer(player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP);
            }

        }
        //DemocracyLife.LOGGER.info("Activated a Gimmick");
        //SessionTranscript.addMessageWithTime("Activated a Gimmick");
    }
}
