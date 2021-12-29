package io.paradaux.dragonrace.events;

import io.paradaux.dragonrace.team.ETeam;
import io.paradaux.dragonrace.team.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (TeamManager.hasTeam(event.getPlayer())) {
            ETeam team = TeamManager.getTeam(event.getPlayer());
        }
    }

}
