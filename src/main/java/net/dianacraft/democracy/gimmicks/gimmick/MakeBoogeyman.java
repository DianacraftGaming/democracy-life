package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.*;

public class MakeBoogeyman extends Gimmick {
    public ServerPlayerEntity player;
    private boolean revealBoogeyman = false;

    public MakeBoogeyman(){
        player = randomisePlayer();
        if (seasonConfig.getProperty("reveal_boogeyman").equalsIgnoreCase("true"))
            revealBoogeyman = true;
        if (player != null && revealBoogeyman){
            voteText = player.getNameForScoreboard() + "turns into a Boogeyman.";
        } else {
            voteText = "A random non-red player turns into a Boogeyman";
        }
    }

    public boolean isAvailable(){
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
        PlayerUtils.sendTitle(player, Text.literal("You are now The Boogeyman").formatted(Formatting.RED), 10, 40, 10);
        PlayerUtils.playSoundToPlayer(player, SoundEvent.of(Identifier.of("minecraft", "lastlife_boogeyman_yes")));
        DemocracyLife.LOGGER.info("[Gimmick] Made " + player.getNameForScoreboard() + " into a Boogeyman.");
        SessionTranscript.addMessageWithTime("[Gimmick] Made " + player.getNameForScoreboard() + " into a Boogeyman.");
    }
    public void deactivate() {}
}
