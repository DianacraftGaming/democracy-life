package net.dianacraft.democracy.gimmicks;
import net.dianacraft.democracy.gimmicks.gimmick.*;

import java.util.ArrayList;
import java.util.List;

public enum Gimmicks {
    NULL,
    NOTHING_HAPPENS,
    MAKE_BOOGEYMAN,
    PLAYER_DIES,
    LIFE_GAMBLING,
    MAKE_GOLDEN,
    RANDOM_TELEPORT,
    REMARRY,
    GREENS_DIE,
    GET_CAKE,
    JOEY_WINDS,
    COPY_PLAYER,
    DINNER_TIME,
    GET_BEDROCK,
    GET_NUGGETS,
    GET_PORTAL,
    YELLOW_GIFT;

    public Gimmick getInstance(){
        if (this == MAKE_BOOGEYMAN) return new MakeBoogeyman();
        if (this == PLAYER_DIES) return new PlayerDies();
        if (this == NOTHING_HAPPENS) return new NothingHappens();
        if (this == LIFE_GAMBLING) return new LifeGambling();
        if (this == MAKE_GOLDEN) return new MakeGolden();
        if (this == RANDOM_TELEPORT) return new RandomTeleport();
        if (this == REMARRY) return new Remarry();
        if (this == GREENS_DIE) return new GreensDie();
        if (this == GET_CAKE) return new GetCake();
        if (this == JOEY_WINDS) return new JoeyWinds();
        if (this == COPY_PLAYER) return new CopyPlayer();
        if (this == DINNER_TIME) return new DinnerTime();
        if (this == GET_BEDROCK) return new GetBedrock();
        if (this == GET_NUGGETS) return new GetNuggets();
        if (this == GET_PORTAL) return new GetPortal();
        if (this == YELLOW_GIFT) return new YellowGift();

        return null;
    }

    public String getStringName() {
        return this.toString().toLowerCase();
    }

    public static Gimmicks getFromString(String gimmick) {
        try {
            return Enum.valueOf(Gimmicks.class, gimmick.toUpperCase());
        } catch(Exception ignored) {}
        return Gimmicks.NULL;
    }

    public static List<Gimmicks> getGimmicks() {
        List<Gimmicks> gimmicks = new ArrayList<>(List.of(Gimmicks.values()));
        gimmicks.remove(Gimmicks.NULL);
        gimmicks.remove(Gimmicks.NOTHING_HAPPENS);
        return gimmicks;
    }

    public static List<String> getGimmicksStr() {
        List<String> result = new ArrayList<>();
        for (Gimmicks gimmick : getGimmicks()) {
            String name = gimmick.getStringName();
            result.add(name);
        }
        return result;
    }

    public static List<Gimmicks> getActiveGimmicks() {
        return new ArrayList<>(GimmickManager.activeGimmicks.keySet());
    }

    public static List<String> getActiveGimmicksStr() {
        List<String> result = new ArrayList<>();
        for (Gimmicks gimmick : getActiveGimmicks()) {
            String name = gimmick.getStringName();
            result.add(name);
        }
        return result;
    }

    public static List<Gimmicks> getInactiveGimmicks() {
        List<Gimmicks> result = new ArrayList<>(getGimmicks());
        result.removeAll(getActiveGimmicks());
        return result;
    }

    public static List<String> getInactiveGimmicksStr() {
        List<String> result = new ArrayList<>();
        for (Gimmicks gimmick : getInactiveGimmicks()) {
            String name = gimmick.getStringName();
            result.add(name);
        }
        return result;
    }
}
