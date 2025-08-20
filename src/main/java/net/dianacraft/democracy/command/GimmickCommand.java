package net.dianacraft.democracy.command;

import com.mojang.brigadier.CommandDispatcher;
import net.mat0u5.lifeseries.seasons.season.Seasons;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.Main.livesManager;
import static net.mat0u5.lifeseries.utils.player.PermissionManager.isAdmin;
import static net.minecraft.server.command.CommandManager.literal;

public class GimmickCommand {
    public static boolean isAllowed() {
        return currentSeason.getSeason() == Seasons.REAL_LIFE;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(
                literal("gimmick").requires(source -> isAllowed() && (isAdmin(source.getPlayer()) || (source.getEntity() == null)))
                        .then(literal("activate").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("Nothing to activate"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
                        .then(literal("deactivate").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("Nothing to deactivate"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
                        .then(literal("list").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("There are no gimmicks yet"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
                        .then(literal("listActive").executes(
                                context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                    if (player != null) {
                                        player.sendMessage(Text.of("No gimmicks are active"));
                                        return 1;
                                    }
                                    return 0;
                                }
                        ))
        );
    }
}
