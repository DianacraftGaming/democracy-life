package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WindChargeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.mat0u5.lifeseries.Main.*;

public class JoeyWinds extends Gimmick {
    public JoeyWinds(){
        voteText = "JoeyNotJoey winds";
    }

    public boolean isAvailable(){
        if (seasonConfig.getProperty("relife_mode").equalsIgnoreCase("false")) {
            return false;
        } else {
            List<String> usernames = new ArrayList<>();
            for (ServerPlayerEntity player : currentSeason.livesManager.getAlivePlayers()) {
                usernames.add(player.getNameForScoreboard());
            }
            return (usernames.contains("JoeyNotJoey_"));
        }
    }

    @Override
    public void activate() {
        if (isAvailable()){
            ServerPlayerEntity joey = PlayerUtils.getPlayer("JoeyNotJoey_");

            if (joey != null) {
                ItemStack is = new ItemStack(Registries.ITEM.get(Identifier.of("minecraft:wind_charge")), 4);
                joey.giveOrDropStack(is);
            }
        }
    }
}
