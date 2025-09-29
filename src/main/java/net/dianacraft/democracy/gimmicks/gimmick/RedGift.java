package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class RedGift extends Gimmick {
    @Override
    public boolean isAvailable() {
        for (ServerPlayerEntity player: livesManager.getAlivePlayers()){
            if (livesManager.isOnSpecificLives(player, 1)) return true;
        }
        return false;
    }

    public RedGift(){
        voteText = "All reds get either 10 diamonds or 3 golden apples";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            if (livesManager.isOnSpecificLives(player, 1)) {
                if (Math.random() < 0.5) {
                    ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:diamond")), 10);
                    player.giveOrDropStack(is);
                    DemocracyLife.LOGGER.info("[Gimmick] Gave diamonds to" + player.getNameForScoreboard());
                    SessionTranscript.addMessageWithTime("[Gimmick] Gave diamonds to" + player.getNameForScoreboard());
                } else {
                    ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:golden_apple")), 3);
                    player.giveOrDropStack(is);
                    DemocracyLife.LOGGER.info("[Gimmick] Gave golden apples to" + player.getNameForScoreboard());
                    SessionTranscript.addMessageWithTime("[Gimmick] Gave golden apples to" + player.getNameForScoreboard());
                }
            }
        }
    }
}
