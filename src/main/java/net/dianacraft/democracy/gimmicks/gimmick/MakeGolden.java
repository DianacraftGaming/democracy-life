package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;

public class MakeGolden extends Gimmick {
    public String voteText = "Activate a Gimmick";

    public static boolean isAvailable(){
        return false;
    }

    public void activate() {
        DemocracyLife.LOGGER.info("Activated a Gimmick");
        SessionTranscript.addMessageWithTime("Activated a Gimmick");
    };
    public void deactivate() {
        DemocracyLife.LOGGER.info("Deactivated a Gimmick");
        SessionTranscript.addMessageWithTime("Deactivated a Gimmick");
    };
}
