package io.paradaux.dragonrace.team;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TeamManager {

    private static final HashMap<String, ETeam> teams = new HashMap<>();

    public static boolean hasTeam(Player player) {
        return findContestant(player) != null;
    }

    public static void addTeam(String name, int color, String glowingColor) {
        teams.put(name, new ETeam(name, color, glowingColor));
    }

    public static void removeTeam(String name) {
        teams.get(name).destroy();
        teams.remove(name);
    }

    public static ETeam getTeam(String name) {
        return teams.get(name);
    }

    public static ETeam getTeam(Player player) {
        for (ETeam t : teams.values()) {
            for (Contestant c : t.getContestants()) {
                if (c.getPlayer().equals(player)) {
                    return t;
                }
            }
        }
        return null;
    }

    public static Collection<ETeam> getTeams() {
        return teams.values();
    }

    public static void registerPlayer(String team, Player player) {
        teams.get(team).addContestant(player);
    }

    public static void deregisterPlayer(String team, Player player) {
        teams.get(team).removeContestant(player);
    }

    public static Contestant findContestant(Player player) {
        for (ETeam t : teams.values()) {
            for (Contestant c : t.getContestants()) {
                if (c.getPlayer().equals(player)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static void givePoint(Player player) {
        Contestant c = findContestant(player);

        if (c == null) {
            return;
        }

        c.incrementScore();
    }
}
