package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.other.OtherUtils;
import net.mat0u5.lifeseries.utils.other.TextUtils;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.border.WorldBorder;

import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.server;

public class RandomTeleport extends Gimmick {
    public RandomTeleport(){
        voteText = "Everyone gets a teleported randomly";
    }

    public static boolean isAvailable(){
        return true;
    }

    @Override
    public void activate() {
        if (server == null) return;
        List<ServerPlayerEntity> players = currentSeason.livesManager.getAlivePlayers();
        if (players.isEmpty()) return;


        for (ServerPlayerEntity player : players) {
            PlayerUtils.playSoundToPlayer(player, SoundEvents.ENTITY_ENDERMAN_TELEPORT);
            player.sendMessage(Text.of("§6Woosh!"));
        }

        WorldBorder border = server.getOverworld().getWorldBorder();
        OtherUtils.executeCommand(TextUtils.formatString("spreadplayers {} {} 0 {} false @a", (int)Math.round(border.getCenterX()), (int)Math.round(border.getCenterZ()), (int)Math.round((border.getSize()/2))));

        for (ServerPlayerEntity player : players) {
            player.removeCommandTag("randomTeleport");
        }

        DemocracyLife.LOGGER.info("[Gimmick] Teleported everyone randomly");
        SessionTranscript.addMessageWithTime("[Gimmick] Teleported everyone randomly");
    }
}
