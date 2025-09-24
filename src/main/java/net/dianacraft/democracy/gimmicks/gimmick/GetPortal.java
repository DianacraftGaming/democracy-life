package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class GetPortal extends Gimmick {
    public boolean isAvailable() {
        return true;
    }

    public GetPortal(){
        voteText = "Everyone gets the materials for a Nether Portal";
    }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:obsidian")), 10);
            player.giveOrDropStack(is);
            is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:flint_and_steel")), 1);
            player.giveOrDropStack(is);
        }
    }
}
