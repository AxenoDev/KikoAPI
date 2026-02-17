package fr.kikoplugins.kikoapi.hook;

import fr.kikoplugins.kikoapi.KikoAPI;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HookManager {

    private final List<AbstractHook> hooks = new ArrayList<>();
    private final KikoAPI plugin;

    public HookManager(KikoAPI plugin) {
        this.plugin = plugin;
    }

    public void registerHook(AbstractHook hook) {
        Plugin target = this.plugin.getServer()
                .getPluginManager()
                .getPlugin(hook.getName());

        if (target == null) {
            this.plugin.getSLF4JLogger().warn("Cannot hook into {}: plugin not found", hook.getName());
            return;
        }

        hooks.add(hook);
        this.plugin.getSLF4JLogger().info("{} hook registered", hook.getName());
    }

    public void registerHooks(AbstractHook... hooks) {
        for (AbstractHook hook : hooks) {
            registerHook(hook);
        }
    }

    public void enableHooks() {
        hooks.forEach(AbstractHook::enable);
    }

    public void disableHooks() {
        hooks.forEach(AbstractHook::disable);
    }

    public void onJoin(PlayerJoinEvent event) {
        hooks.forEach(hook -> hook.onJoin(event));
    }

    public void onQuit(PlayerQuitEvent event) {
        hooks.forEach(hook -> hook.onQuit(event));
    }

}
