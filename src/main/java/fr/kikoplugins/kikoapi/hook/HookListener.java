package fr.kikoplugins.kikoapi.hook;

import fr.kikoplugins.kikoapi.KikoAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class HookListener implements Listener {

    private final HookManager hookManager = KikoAPI.getHookManager();

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        hookManager.enableHooks();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        hookManager.onJoin(event);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        hookManager.onQuit(event);
    }

}
