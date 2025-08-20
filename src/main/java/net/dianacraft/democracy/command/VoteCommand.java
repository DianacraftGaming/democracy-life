package net.dianacraft.democracy.command;

import com.mojang.brigadier.CommandDispatcher;
import net.mat0u5.lifeseries.seasons.season.Seasons;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.utils.player.PermissionManager.isAdmin;
import static net.minecraft.server.command.CommandManager.literal;

public class VoteCommand {
    public static boolean isAllowed() {
        return currentSeason.getSeason() == Seasons.REAL_LIFE;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(
                literal("vote").requires(source -> isAllowed())
                        .then(literal("start").requires(source -> (isAdmin(source.getPlayer()) || (source.getEntity() == null))).executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("hi icey this is the main thing I'm working on rn"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
                        .then(literal("option1").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("No vote in progress"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
                        .then(literal("option2").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("No vote in progress"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
        );
    }
}
