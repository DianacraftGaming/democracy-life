package net.dianacraft.democracy.gimmicks.gimmick;

import net.dianacraft.democracy.DemocracyLife;
import net.dianacraft.democracy.gimmicks.Gimmick;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;

public class DoNothing extends Gimmick {
    public void activate() {
        DemocracyLife.LOGGER.info("[Gimmick] Majority chose to Do Nothing");
        SessionTranscript.addMessageWithTime("[Gimmick] Majority chose to Do Nothing");
    }
    public void deactivate() {}
}
