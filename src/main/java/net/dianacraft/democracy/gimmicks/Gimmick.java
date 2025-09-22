package net.dianacraft.democracy.gimmicks;

import net.dianacraft.democracy.DemocracyLife;
import net.mat0u5.lifeseries.seasons.session.SessionTranscript;
import net.minecraft.server.network.ServerPlayerEntity;

public abstract class Gimmick {
    public boolean active = false;
    public int length = -1;
    public String voteText = "Activate a Gimmick";
    public ServerPlayerEntity player = null;

    public boolean isAvailable(){
        return false;
    }

    public void activate() {
        DemocracyLife.LOGGER.info("[Gimmick] Activated a Gimmick");
        SessionTranscript.addMessageWithTime("[Gimmick] Activated a Gimmick");
    };
    public void deactivate() {
        DemocracyLife.LOGGER.info("[Gimmick] Deactivated a Gimmick");
        SessionTranscript.addMessageWithTime("[Gimmick] Deactivated a Gimmick");
    };

    public void tickSessionOn() {}
    public void tick() {}
    public void softTick() {}
}
