package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.*;

public class CopyPlayer extends Gimmick {
    ServerPlayerEntity player;
    boolean revealPlayers = false;

    @Override
    public boolean isAvailable() {
        //return (!livesManager.getAlivePlayers().isEmpty()) && FabricLoader.getInstance().isModLoaded("lifeskins");
        return false;
    }

    private ServerPlayerEntity randomisePlayer(){
        List<ServerPlayerEntity> players = livesManager.getAlivePlayers();
        Collections.shuffle(players);
        return players.getFirst();
    }

    public CopyPlayer(){
        player = randomisePlayer();
        if (seasonConfig.getProperty("reveal_players").equalsIgnoreCase("true"))
            revealPlayers = true;
        if (player != null && revealPlayers){
            voteText = "Everyone copies " + player.getNameForScoreboard();
        } else {
            voteText = "Everyone copies a random player";
        }
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            player.sendMessage(Text.of("b ."));
        }
    }
}
