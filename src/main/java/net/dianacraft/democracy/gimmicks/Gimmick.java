package net.dianacraft.democracy.gimmicks;

import net.dianacraft.democracy.DemocracyLife;

public abstract class Gimmick {
    public boolean active = false;
    public int length = -1;

    public void activate() {
        DemocracyLife.LOGGER.info("Activated a Gimmick");
    };
    public void deactivate() {
        DemocracyLife.LOGGER.info("Deactivated a Gimmick");
    };

    public void tickSessionOn() {}
    public void tick() {}
    public void softTick() {}
}
