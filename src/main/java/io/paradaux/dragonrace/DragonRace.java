package io.paradaux.dragonrace;

import io.paradaux.dragonrace.events.PlayerJoinListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DragonRace extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic



        registerEvents(getServer().getPluginManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents(PluginManager man) {
        man.registerEvents(new PlayerJoinListener(), this);
    }

    private void registerTasks() {

    }

}
