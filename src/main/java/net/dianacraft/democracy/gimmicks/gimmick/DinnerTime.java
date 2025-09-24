package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class DinnerTime extends Gimmick {
    public boolean isAvailable() {
        return true;
    }

    public DinnerTime(){
        voteText = "Everyone gets dinner";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            double seed = Math.random();
            ItemStack is;

            if (seed < 0.25){
                is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:cooked_beef")), 32);
            } else if (seed < 0.5){
                is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:cooked_porkchop")), 32);
            } else if (seed < 0.75){
                is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:apple")), 32);
            } else {
                is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:golden_carrot")), 32);
            }

            player.giveOrDropStack(is);
        }
    }
}
