package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class GetNuggets extends Gimmick {
    @Override
    public boolean isAvailable() {
        return true;
    }

    public GetNuggets(){
        voteText = "Everyone gets nuggets";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:gold_nugget")), 64);
            player.giveOrDropStack(is);
        }
    }
}
