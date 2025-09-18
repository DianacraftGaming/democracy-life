package net.dianacraft.democracy.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.dianacraft.democracy.gimmicks.GimmickManager;
import net.dianacraft.democracy.gimmicks.Gimmicks;
import net.mat0u5.lifeseries.seasons.season.Seasons;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;

import static net.dianacraft.democracy.gimmicks.GimmickManager.activeVote;
import static net.mat0u5.lifeseries.Main.*;
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
                                    validText(false, context);
                                    GimmickManager.prepareVotes(false);
                                    return 0;
                                }
                        ))
                        .then(literal("end").requires(source -> (isAdmin(source.getPlayer()) || (source.getEntity() == null))).executes(
                                context -> {
                                    validText(true, context);
                                    GimmickManager.finaliseVotes(false);
                                    return 0;
                                }
                        ))
                        .then(argument("gimmick", StringArgumentType.greedyString())
                            .suggests((context, builder) -> CommandSource.suggestMatching(suggestionsVoteGimmick(), builder))
                            .executes(VoteCommand::vote
                        ))
        );
    }

    public static void validText(boolean invert, CommandContext<ServerCommandSource> context){
        boolean isActive = activeVote;
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) return;

        if (isActive && !invert){
            player.sendMessage(Text.of("§cThere is already a vote in progress."), false);
        } else if (invert && !isActive) {
            player.sendMessage(Text.of("§cThere is no vote in progress."), false);
        }
    }

    public static List<String> suggestionsVoteGimmick() {
        List<String> voteOptions = GimmickManager.getVoteGimmicksStr();
        //allGimmicks.add("*");
        return voteOptions;
    }

    public static int vote(CommandContext<ServerCommandSource> context){
        if (!activeVote){
            validText(true, context);
            return 0;
        }
        ServerPlayerEntity player = context.getSource().getPlayer();
        if (player == null) return 0;
        String gimmick = StringArgumentType.getString(context, "gimmick");

        if (!livesManager.getAlivePlayers().contains(player)) {
            player.sendMessage(Text.of("§cYour choice doesn't matter, just enjoy the show."), false);
            return 0;
        }
        if (seasonConfig.getProperty("vote_recast").equalsIgnoreCase("false") && GimmickManager.playerVotes.containsKey(player)){
            player.sendMessage(Text.of("§eYou already voted in this poll!"), false);
            return 0;
        }
        GimmickManager.playerVotes.put(player, Gimmicks.getFromString(gimmick));
        player.sendMessage(Text.of("§aVoted for " + gimmick + " successfully!"), false);
        return 1;
    }
}
