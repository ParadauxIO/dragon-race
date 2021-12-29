package io.paradaux.dragonrace.events;

import io.paradaux.dragonrace.DragonRace;
import io.paradaux.dragonrace.team.TeamManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.inventivetalent.glow.GlowAPI;

public class PlayerDeathListener implements Listener {

    private final DragonRace plugin;

    public PlayerDeathListener(DragonRace plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (TeamManager.hasTeam(event.getPlayer())) {
            TeamManager.givePoint(event.getPlayer());
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            Component title = Component.text("You died.").color(NamedTextColor.DARK_AQUA);
            Component subtitle = Component.text("Take a drink!").color(NamedTextColor.AQUA);

            if (event.getPlayer().getName().equals("marycoyne")) {
                subtitle = Component.text("Try not to die again..").color(NamedTextColor.RED);
            }

            Title t = Title.title(title, subtitle);
            event.getPlayer().showTitle(t);
        }, 20 * 5);
    }

}
