package io.paradaux.dragonrace;

import io.paradaux.dragonrace.adventure.AdventureImpl;
import io.paradaux.dragonrace.command.EventCommand;
import io.paradaux.dragonrace.events.PlayerDeathListener;
import io.paradaux.dragonrace.events.PlayerJoinListener;

import io.paradaux.dragonrace.tasks.GlowTask;
import io.paradaux.dragonrace.tasks.ManageScoreboardTask;
import io.paradaux.dragonrace.team.ScoreboardManager;
import io.paradaux.dragonrace.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class DragonRace extends JavaPlugin {

    private static AdventureImpl adventure;
    private static ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        adventure = new AdventureImpl(this);
        scoreboardManager = new ScoreboardManager();

        registerCommands();
        registerEvents(getServer().getPluginManager());
        registerTasks();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        this.getCommand("event").setExecutor(new EventCommand());
    }

    private void registerEvents(PluginManager man) {
        man.registerEvents(new PlayerJoinListener(), this);
        man.registerEvents(new PlayerDeathListener(this), this);
    }

    private void registerTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ManageScoreboardTask(), 10, 30);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new GlowTask(), 10, 60);
    }
}
