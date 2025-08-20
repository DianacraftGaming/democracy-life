package net.dianacraft.democracy.gimmicks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GimmickManager {
    public static final Map<Gimmicks, Gimmick> activeGimmicks = new HashMap<>();
    public static final Random rnd = new Random();

    public void prepareVotes(){};

    public static boolean isActiveGimmick(Gimmicks gimmick){
        return false;
    }
}
