package io.paradaux.dragonrace.tasks;

import io.paradaux.dragonrace.team.Contestant;
import io.paradaux.dragonrace.team.ETeam;
import io.paradaux.dragonrace.team.TeamManager;
import org.bukkit.Bukkit;
import org.inventivetalent.glow.GlowAPI;

public class GlowTask implements Runnable {
    @Override
    public void run() {
        Bukkit.getLogger().info("GlowTask");
        for (ETeam t : TeamManager.getTeams()) {
            for (Contestant c : t.getContestants()) {
                for (Contestant c2 : t.getContestants()) {
                    if (c.equals(c2)) {
                        continue;
                    }
                    GlowAPI.setGlowing(c.getPlayer(), t.getGlowingColor(), c2.getPlayer());
                }
            }
        }

    }
}
