package net.dianacraft.democracy.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.dianacraft.democracy.gimmicks.GimmickManager;
import net.dianacraft.democracy.gimmicks.Gimmicks;
import net.mat0u5.lifeseries.seasons.season.Seasons;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.utils.player.PermissionManager.isAdmin;
import static net.minecraft.server.command.CommandManager.argument;
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
                                        GimmickManager.prepareVotes();
                                    }
                                    return 0;
                                }
                        ))
                        .then(argument("gimmick", StringArgumentType.greedyString())
                            .suggests((context, builder) -> CommandSource.suggestMatching(suggestionsVoteGimmick(), builder))
                            .executes(context -> {
                                return 1;
                            }
                        ))
        );
    }

    public static List<String> suggestionsVoteGimmick() {
        List<String> voteOptions = GimmickManager.getVoteGimmicksStr();
        //allGimmicks.add("*");
        return voteOptions;
    }
}
