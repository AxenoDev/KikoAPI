package fr.kikoplugins.kikoapi;

import fr.kikoplugins.kikoapi.hook.HookListener;
import fr.kikoplugins.kikoapi.hook.HookManager;
import fr.kikoplugins.kikoapi.hook.impl.LuckPermsHook;
import fr.kikoplugins.kikoapi.hook.impl.PlaceholderAPIHook;
import fr.kikoplugins.kikoapi.lang.Lang;
import fr.kikoplugins.kikoapi.updatechecker.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class KikoAPI extends JavaPlugin {
    private static final String MODRINTH_PROJECT_ID = "nwOXHH0K";
    private static final int BSTATS_PLUGIN_ID = 29448;
    public static Lang LANG;
    private static KikoAPI instance;
    private static HookManager hookManager;
    private Metrics bStats;

    public static boolean isUnitTest() {
        return Bukkit.getVersion().contains("MockBukkit");
    }

    public static KikoAPI getInstance() {
        return instance;
    }

    public static HookManager getHookManager() {
        return hookManager;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        LANG = Lang.builder(this)
                .addDefaultLanguageFiles("en_US.yml", "fr_FR.yml")
                .build();

        if (!isUnitTest())
            this.bStats = new Metrics(this, BSTATS_PLUGIN_ID);

        hookManager = new HookManager(this);

        this.getServer().getPluginManager().registerEvents(new HookListener(), this);

        if (this.getConfig().getBoolean("update-checker.enabled", true))
            new UpdateChecker(this, MODRINTH_PROJECT_ID);
    }

    @Override
    public void onDisable() {
        if (!isUnitTest())
            this.bStats.shutdown();
    }

    public void reload() {
        this.getSLF4JLogger().info("Reloading KikoAPI...");

        this.reloadConfig();
        LANG.reload();

        this.getSLF4JLogger().info("KikoAPI reloaded.");
    }
}
