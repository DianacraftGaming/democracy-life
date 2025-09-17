package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;

public class NothingHappens extends Gimmick {

    public NothingHappens(){
        voteText = "Nothing Happens, game continues as normal.";
    }

    public void activate() {
        DemocracyLife.LOGGER.info("[Gimmick] Majority chose for Nothing to Happen");
        SessionTranscript.addMessageWithTime("[Gimmick] Majority chose for Nothing to Happen");
    }
    public void deactivate() {}
}
