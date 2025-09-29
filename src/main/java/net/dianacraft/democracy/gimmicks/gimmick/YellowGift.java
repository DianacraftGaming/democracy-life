package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class YellowGift extends Gimmick {
    @Override
    public boolean isAvailable() {
        return livesManager.anyYellowPlayers(null);
    }

    public YellowGift(){
        voteText = "All yellows get a netherite ingot and an upgrade template";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            if (livesManager.isOnSpecificLives(player, 2)) {
                ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:netherite_ingot")), 1);
                player.giveOrDropStack(is);
                is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:netherite_upgrade_smithing_template")), 1);
                player.giveOrDropStack(is);

                DemocracyLife.LOGGER.info("[Gimmick] Gave Netherite to" + player.getNameForScoreboard());
                SessionTranscript.addMessageWithTime("[Gimmick] Gave Netherite to" + player.getNameForScoreboard());
            }
        }
    }
}
