package io.paradaux.dragonrace.adventure;

import io.paradaux.dragonrace.DragonRace;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * AdventureImpl is a wrapper around the kyori/adventure Framework for Minecraft Components.
 * @author Rían Errity
 * @since 2.0.0
 * */
public class AdventureImpl implements AutoCloseable {

    private static AdventureImpl instance;

    /**
     * Singleton field containing the MiniMessage parser in use.
     * */
    private static MiniMessage miniMessage;

    /**
     * Singleton access to the adventure implementation.
     * */
    public static AdventureImpl getInstance() {

        return instance;
    }

    /**
     * The Adventure API
     * */
    private BukkitAudiences adventure;

    /**
     * Initialise the Adventure Implementation using the Bukkit Platform.
     * */
    public AdventureImpl(DragonRace plugin) {
        this.adventure = BukkitAudiences.create(plugin);
        miniMessage = MiniMessage.get();

        AdventureImpl.instance = this;
    }

    /**
     * Sends a chat component to the specified {@link Player}, interpreting the provided String as a MiniMessage-serialised Adventure Component.
     * @param player The player you wish to send the component to
     * @param message A MiniMessage-serialised Adventure component.
     * */
    public void sendMiniMessage(Player player, String message) {
        adventure().player(player).sendMessage(miniMessage.parse(message));
    }

    /**
     * Sends a chat component to the specified {@link CommandSender}, interpreting the provided String as a MiniMessage-serialised Adventure Component.
     * @param sender The sender you wish to send the component to
     * @param message A MiniMessage-serialised Adventure component.
     * */
    public void sendMiniMessage(CommandSender sender, String message) {
        adventure().sender(sender).sendMessage(miniMessage.parse(message));
    }

    /**
     * Returns the BukkitAudience Instance.
     * @return BukkitAudiences
     * @throws IllegalStateException Thrown when the audience is invalid.
     * */
    @NonNull
    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Adventure is null / closed");
        }
        return this.adventure;
    }

    /**
     * Disables the Adventure integration, preventing further use internally and by dependencies.
     * */
    @Override
    public void close() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
}