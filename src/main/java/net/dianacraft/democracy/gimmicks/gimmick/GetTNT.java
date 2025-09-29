package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.mat0u5.lifeseries.Main.livesManager;

public class GetTNT extends Gimmick {
    public boolean isAvailable() { return true; }

    public GetTNT(){ voteText = "Everyone gets tnt"; }

    @Override
    public void activate() {
        for (ServerPlayerEntity player : livesManager.getAlivePlayers()){
            ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:tnt")), 16);
            player.giveOrDropStack(is);
        }
    }
}
