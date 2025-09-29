package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionAction;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.mat0u5.lifeseries.utils.player.TeamUtils;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.Formatting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static net.mat0u5.lifeseries.Main.currentSession;
import static net.mat0u5.lifeseries.Main.seasonConfig;
import static net.mat0u5.lifeseries.utils.other.OtherUtils.minutesToTicks;

public class HideLives extends Gimmick {
    private Map<String, Formatting> defaultFormattings = new HashMap<>();

    @Override
    public boolean isAvailable() { return true; }

    public HideLives() {
        voteText = "Everyone's lives are hidden for " + seasonConfig.getProperty("teamhide_duration") + " minutes";
        length = minutesToTicks(Integer.parseInt(seasonConfig.getProperty("teamhide_duration")));
    }

    @Override
    public void activate() {
        for (Team team : TeamUtils.getAllTeams()){
            if (team.getName().equals("golden") && Boolean.parseBoolean(seasonConfig.getProperty("hide_goldens"))){
                continue;
            }
            defaultFormattings.put(team.getName(), team.getColor());
            team.setColor(Formatting.GRAY);
        }
        currentSession.addSessionAction(new SessionAction(currentSession.getPassedTime() + length) {
            @Override
            public void trigger() {
                deactivate();
            }
        });
        DemocracyLife.LOGGER.info("[Gimmick] Hid all the teams");
        SessionTranscript.addMessageWithTime("[Gimmick] Hid all the teams");
    }

    @Override
    public void deactivate() {
        for (Team team : TeamUtils.getAllTeams()){
            if (defaultFormattings.containsKey(team.getName())){
                team.setColor(defaultFormattings.get(team.getName()));
            }
        }
        DemocracyLife.LOGGER.info("[Gimmick] Revealed all the teams");
        SessionTranscript.addMessageWithTime("[Gimmick] Revealed all the teams");
    }
}
