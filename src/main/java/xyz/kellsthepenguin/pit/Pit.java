package xyz.kellsthepenguin.pit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kellsthepenguin.pit.commands.PitCommand;
import xyz.kellsthepenguin.pit.events.OnAttack;
import xyz.kellsthepenguin.pit.events.OnDeath;
import xyz.kellsthepenguin.pit.events.OnJoin;

import java.util.HashMap;

public final class Pit extends JavaPlugin {
    public static HashMap<String, Boolean> playingStatuses = new HashMap<>();
    public static FileConfiguration configuration;

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        this.saveDefaultConfig();
        configuration = this.getConfig();
        manager.registerEvents(new OnJoin(), this);
        manager.registerEvents(new OnAttack(), this);
        manager.registerEvents(new OnDeath(), this);

        assert false;
        getCommand("pit").setExecutor(new PitCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
