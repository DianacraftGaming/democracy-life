package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.dianacraft.democracy.gimmicks.Gimmicks;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.mat0u5.lifeseries.utils.player.TeamUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static net.dianacraft.democracy.gimmicks.GimmickManager.activeGimmicks;
import static net.mat0u5.lifeseries.Main.*;

public class MakeGolden extends Gimmick {
    public ServerPlayerEntity player;
    private boolean revealPlayers = false;

    public MakeGolden(){
        player = randomisePlayer();
        if (seasonConfig.getProperty("reveal_players").equalsIgnoreCase("true"))
            revealPlayers = true;
        if (player != null && revealPlayers){
            voteText = player.getNameForScoreboard() + " becomes golden. Anyone can kill them to gain a life";
        } else {
            voteText = "A random player becomes Golden. Anyone can kill them to gain a life";
        }
    }

    private ServerPlayerEntity randomisePlayer(){
        List<ServerPlayerEntity> players = livesManager.getAlivePlayers();
        if (!players.isEmpty()) {
            Collections.shuffle(players);
            return players.getFirst();
        }
        return null;
    }

    public static boolean isAvailable(){
        return true;
    }

    public void activate() {
        TeamUtils.addEntityToTeam("golden", player);
        DemocracyLife.LOGGER.info("[Gimmick] "+player.getNameForScoreboard()+" is now Golden");
        SessionTranscript.addMessageWithTime("[Gimmick] "+player.getNameForScoreboard()+" is now Golden");
        PlayerUtils.sendTitle(player, Text.literal("You are now Golden").formatted(Formatting.GOLD), 10, 30, 10);
        PlayerUtils.playSoundToPlayer(player, SoundEvents.BLOCK_CHAIN_BREAK);

        active = true;
        activeGimmicks.put(Gimmicks.MAKE_GOLDEN, this);
    };
    public void deactivate() {
        if (Objects.equals(TeamUtils.getPlayerTeam(player).getName(), "golden")){
            currentSeason.reloadPlayerTeam(player);
        }
        DemocracyLife.LOGGER.info("[Gimmick] "+player.getNameForScoreboard()+" is no longer Golden");
        SessionTranscript.addMessageWithTime("[Gimmick] "+player.getNameForScoreboard()+" is no longer Golden");

        active = false;
        activeGimmicks.remove(Gimmicks.MAKE_GOLDEN);
    };
}
