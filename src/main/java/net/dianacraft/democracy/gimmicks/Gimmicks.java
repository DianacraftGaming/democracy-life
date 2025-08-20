package net.dianacraft.democracy.gimmicks;
import net.dianacraft.democracy.gimmicks.gimmick.*;

import java.util.ArrayList;
import java.util.List;

public enum Gimmicks {
    NULL,
    MAKE_BOOGEYMAN,
    PLAYER_DIES;

    public Gimmick getInstance(){
        if (this == MAKE_BOOGEYMAN) return new MakeBoogeyman();
        if (this == PLAYER_DIES) return new PlayerDies();

        return null;
    }

    public String getStringName() {
        if (this == NULL) return "none";
        return this.toString().toLowerCase();
    }

    public static Gimmicks getFromString(String gimmick) {
        try {
            return Enum.valueOf(Gimmicks.class, gimmick.toUpperCase());
        } catch(Exception e) {}
        return Gimmicks.NULL;
    }

    public static List<Gimmicks> getGimmicks() {
        List<Gimmicks> gimmicks = new ArrayList<>(List.of(Gimmicks.values()));
        gimmicks.remove(Gimmicks.NULL);
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
