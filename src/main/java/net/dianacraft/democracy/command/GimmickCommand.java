package net.dianacraft.democracy.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.dianacraft.democracy.gimmicks.Gimmicks;
import net.mat0u5.lifeseries.seasons.season.Seasons;
import net.mat0u5.lifeseries.utils.other.OtherUtils;
import net.mat0u5.lifeseries.utils.other.TextUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.dianacraft.democracy.gimmicks.GimmickManager;

import java.util.List;

import static net.mat0u5.lifeseries.Main.currentSeason;
import static net.mat0u5.lifeseries.utils.player.PermissionManager.isAdmin;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class GimmickCommand {
    public static boolean isAllowed() {
        return currentSeason.getSeason() == Seasons.REAL_LIFE;
    }

    public static boolean checkBanned(ServerCommandSource source) {
        if (isAllowed()) return false;
        source.sendError(Text.of("This command is only available when playing Choose Your Life."));
        return true;
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(
                literal("gimmick").requires(source -> isAllowed() && (isAdmin(source.getPlayer()) || (source.getEntity() == null)))
                        .then(literal("activate")
                                .then(argument("gimmick", StringArgumentType.greedyString())
                                .suggests((context, builder) -> CommandSource.suggestMatching(suggestionsActivateGimmick(), builder))
                                .executes(context -> activateGimmick(
                                        context.getSource(), StringArgumentType.getString(context, "gimmick"))
                                )
                        ))
                        .then(literal("deactivate")
                                .then(argument("gimmick", StringArgumentType.greedyString())
                                .suggests((context, builder) -> CommandSource.suggestMatching(suggestionsDeactivateGimmick(), builder))
                                .executes(context -> deactivateGimmick(
                                        context.getSource(), StringArgumentType.getString(context, "gimmick"))
                                )
                        ))
                        .then(literal("list").executes(
                                context -> listGimmicks(context.getSource())
                        ))
                        .then(literal("listActive").executes(
                                context -> listActiveGimmicks(context.getSource())
                        ))
        );
    }

    public static List<String> suggestionsActivateGimmick() {
        List<String> allGimmicks = Gimmicks.getInactiveGimmicksStr();
        //allGimmicks.add("*");
        return allGimmicks;
    }

    public static List<String> suggestionsDeactivateGimmick() {
        List<String> allGimmicks = Gimmicks.getActiveGimmicksStr();
        //allGimmicks.add("*");
        return allGimmicks;
    }

    public static int listGimmicks(ServerCommandSource source) {
        if (checkBanned(source)) return -1;
        OtherUtils.sendCommandFeedbackQuiet(source, TextUtils.format("Available Gimmicks: {}", Gimmicks.getGimmicksStr()));
        return 1;
    }

    public static int listActiveGimmicks(ServerCommandSource source) {
        if (checkBanned(source)) return -1;
        if (Gimmicks.getActiveGimmicksStr().isEmpty()) {
            OtherUtils.sendCommandFeedbackQuiet(source, Text.of("§7There are no active Gimmicks right now. \nA gimmick can be activated from votes, or you can use '§f/gimmick activate <gimmick>§7' to activate a specific Gimmick right now."));
            return 1;
        }
        OtherUtils.sendCommandFeedbackQuiet(source, TextUtils.format("Activated Gimmicks: {}", Gimmicks.getActiveGimmicksStr()));
        return 1;
    }

    public static int activateGimmick(ServerCommandSource source, String gimmickName){
        if (checkBanned(source)) return -1;
        Gimmicks gimmick = Gimmicks.getFromString(gimmickName);
        if (gimmick == Gimmicks.NULL) {
            source.sendError(Text.of("That Gimmick doesn't exist"));
            return -1;
        }
        if (GimmickManager.isActiveGimmick(gimmick)) {
            source.sendError(Text.of("That Gimmick is already active"));
            return -1;
        }
        Gimmick actualGimmick = gimmick.getInstance();
        if (actualGimmick == null) {
            source.sendError(Text.of("That Gimmick has not been implemented yet"));
            return -1;
        }

        actualGimmick.activate();
        OtherUtils.sendCommandFeedback(source, TextUtils.format("Activated {}", gimmickName));
        return 1;
    }

    public static int deactivateGimmick(ServerCommandSource source, String gimmickName){
        if (checkBanned(source)) return -1;
        Gimmicks gimmick = Gimmicks.getFromString(gimmickName);
        if (gimmick == Gimmicks.NULL) {
            source.sendError(Text.of("That Gimmick doesn't exist"));
            return -1;
        }
        if (!GimmickManager.isActiveGimmick(gimmick)) {
            source.sendError(Text.of("That Gimmick is not active"));
            return -1;
        }
        Gimmick actualGimmick = gimmick.getInstance();
        if (actualGimmick == null) {
            source.sendError(Text.of("That Gimmick has not been implemented yet"));
            return -1;
        }

        actualGimmick.deactivate();
        OtherUtils.sendCommandFeedback(source, TextUtils.format("Deactivated {}", gimmickName));
        return 1;
    }
}
