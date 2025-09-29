package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class RedEnchanter extends Gimmick {
    @Override
    public boolean isAvailable() {
        for (ServerPlayerEntity player: livesManager.getAlivePlayers()){
            if (livesManager.isOnSpecificLives(player, 1)) return true;
        }
        return false;
    }

    public RedEnchanter(){
        voteText = "All reds get an enchanting table";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            if (livesManager.isOnSpecificLives(player, 1)) {
                ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:enchanting_table")), 1);
                player.giveOrDropStack(is);
                DemocracyLife.LOGGER.info("[Gimmick] Gave an enchanter to" + player.getNameForScoreboard());
                SessionTranscript.addMessageWithTime("[Gimmick] Gave an enchanter to" + player.getNameForScoreboard());
            }
        }
    }
}
