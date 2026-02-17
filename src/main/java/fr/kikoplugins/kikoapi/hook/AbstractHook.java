package fr.kikoplugins.kikoapi.hook;

import fr.kikoplugins.kikoapi.KikoAPI;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public abstract class AbstractHook {

    public KikoAPI plugin = KikoAPI.getInstance();
    private boolean enabled = false;

    /**
     * Gets the name of the hook. This should be the name of the plugin to hook into.
     *
     * @return the name of the hook
     */
    public abstract String getName();

    /**
     * Checks if the hook is enabled.
     *
     * @return true if the hook is enabled, false otherwise
     */
    public final boolean isEnabled() {
        return enabled;
    }

    /**
     * Enables the hook. This method should be called by the {@link HookManager#enableHooks()} when the server loads.
     */
    public final void enable() {
        this.plugin.getSLF4JLogger().info("Hook into {}", getName());
        this.enabled = true;
        onEnable();
    }

    /**
     * Disables the hook. This method should be called by the {@link HookManager#disableHooks()} when the server shuts down.
     */
    public final void disable() {
        this.plugin.getSLF4JLogger().info("Unhooked from {}", getName());
        this.enabled = false;
        onDisable();
    }

    /**
     * Called when the hook is enabled.
     * Implementations should not call this method directly, use {@link #enable()} instead.
     */
    protected abstract void onEnable();

    /**
     * Called when the hook is disabled.
     * Implementations should not call this method directly, use {@link #disable()} instead.
     */
    protected abstract void onDisable();

    /**
     * Called when a player joins the server.
     * Implementations should not call this method directly, it is called by the {@link HookListener#onPlayerJoin(PlayerJoinEvent)}.
     *
     * @param event the player join event
     */
    protected abstract void onJoin(PlayerJoinEvent event);

    /**
     * Called when a player quits the server.
     * Implementations should not call this method directly, it is called by the {@link HookListener#onPlayerQuit(PlayerQuitEvent)}.
     *
     * @param event the player quit event
     */
    protected abstract void onQuit(PlayerQuitEvent event);
}
