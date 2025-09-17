package net.dianacraft.democracy.gimmicks;

import net.mat0u5.lifeseries.utils.player.PlayerUtils;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.*;

import static net.mat0u5.lifeseries.Main.currentSeason;

public class GimmickManager {
    public static final Map<Gimmicks, Gimmick> activeGimmicks = new HashMap<>();
    public static Map<Gimmicks, Gimmick> activeVotes = new HashMap<>();
    public static Map<ServerPlayerEntity, Gimmicks> playerVotes = new HashMap<>();
    public static boolean activeVote = false;
    //public static final Random rnd = new Random();

    public static void prepareVotes(){
        if (activeVote) {
            //return;
        }
        activeVote = true;
        activeVotes = new HashMap<>();
        playerVotes = new HashMap<>();
        List<Gimmicks> gimmickList = getValidGimmicks();
        Collections.shuffle(gimmickList);
        int gimmick_count = Integer.parseInt(currentSeason.getConfig().getProperty("gimmick_count"));
        gimmick_count = Integer.min(gimmick_count, Gimmicks.getGimmicks().size());
        gimmick_count = Integer.max(gimmick_count, 1);
        for (int i = 0; i < gimmick_count; i++) {
            activeVotes.put(gimmickList.get(i), gimmickList.get(i).getInstance());
        }
        if (gimmick_count == 1){
            activeVotes.put(Gimmicks.NOTHING_HAPPENS, Gimmicks.NOTHING_HAPPENS.getInstance());
        }
        String template = "§6§l§nChoose Your Life§r\nThe time has come for a new gimmick poll!\nWhichever gimmick gets the most votes will activate in 1 minute!.\nVote with /vote\n\n§nYour options are:§r";
        Text message = Text.of(template + getVoteText());
        for (ServerPlayerEntity player : PlayerUtils.getAllPlayers()) {
            player.sendMessage(message, false);
        }
    }

    public static void finaliseVotes(){
        if (!activeVote) {
            return;
        }
        activeVote = false;
        List<Gimmicks> votes = playerVotes.values().stream().toList();
        Map<Gimmicks, Integer> freqMap = new HashMap<>();
        List<Gimmicks> randomCanditates = new ArrayList<>();

        for (int i  = 0; i < votes.size(); i++){
            freqMap.put(votes.get(i), freqMap.getOrDefault(votes.get(i), 0)+1);
        }

        int maxCount = -1;
        Gimmicks winningVote = Gimmicks.NULL;

        for (Gimmicks gimmick : votes){
            if (freqMap.get(gimmick) > maxCount){
                randomCanditates = new ArrayList<>();
                randomCanditates.add(gimmick);
            } else if (freqMap.get(gimmick) == maxCount) {
                randomCanditates.add(gimmick);
            }
        }

        if (!randomCanditates.isEmpty()){
            Collections.shuffle(randomCanditates);
            winningVote = randomCanditates.get(0);
            activeVotes.get(winningVote).activate();
        }
    }

    public static boolean isActiveGimmick(Gimmicks gimmick){
        return activeGimmicks.containsKey(gimmick);
    }

    public static List<Gimmicks> getValidGimmicks() {
        List<Gimmicks> result = Gimmicks.getGimmicks();
        result.removeIf(gimmick -> activeVotes.containsKey(gimmick));
        return result;
    }

    public static List<Gimmicks> getVoteGimmicks() {
        List<Gimmicks> result = new ArrayList<>();
        for (Gimmicks gimmick : Gimmicks.getGimmicks()) {
            if (activeVotes.containsKey(gimmick)) {
                result.add(gimmick);
            }
        }
        if (activeVotes.containsKey(Gimmicks.NOTHING_HAPPENS)){
            result.add(Gimmicks.NOTHING_HAPPENS);
        }
        return result;
    }

    public static List<String> getVoteGimmicksStr() {
        List<String> result = new ArrayList<>();
        for (Gimmicks gimmick : getVoteGimmicks()) {
            String name = gimmick.getStringName();
            result.add(name);
        }
        return result;
    }

    public static String getVoteText(){
        String result = "";
        for (Map.Entry<Gimmicks, Gimmick> entry : activeVotes.entrySet()){
            result = result + "\n* "+entry.getValue().voteText;
        }
        return result;
    }
}
