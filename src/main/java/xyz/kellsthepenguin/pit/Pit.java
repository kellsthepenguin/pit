package xyz.kellsthepenguin.pit;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.kellsthepenguin.pit.commands.PitCommand;
import xyz.kellsthepenguin.pit.events.OnAttack;
import xyz.kellsthepenguin.pit.events.OnDeath;
import xyz.kellsthepenguin.pit.events.OnJoin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public final class Pit extends JavaPlugin {
    public static HashMap<String, Boolean> playingStatuses = new HashMap<>();
    public static FileConfiguration configuration;
    public static Path userDataPath;

    @Override
    public void onEnable() {
        PluginManager manager = Bukkit.getPluginManager();
        this.saveDefaultConfig();
        configuration = this.getConfig();
        userDataPath = Paths.get(this.getDataFolder().toPath().toString(), "users");
        File userDataFolder = userDataPath.toFile();

        if (!userDataFolder.exists()) userDataFolder.mkdir();

        manager.registerEvents(new OnJoin(), this);
        manager.registerEvents(new OnAttack(), this);
        manager.registerEvents(new OnDeath(), this);

        assert false;
        getCommand("pit").setExecutor(new PitCommand());

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getWorld(configuration.getString("world")).getPlayers()) {
                File playerDataFile = Paths.get(userDataPath.toString(), String.format("%s.yml", p.getUniqueId())).toFile();
                FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        configuration.getString("actionbar")
                                .replaceAll("@k", Integer.toString(playerData.getInt("kills")))
                                .replaceAll("@d", Integer.toString(playerData.getInt("deaths")))
                                .replaceAll("@c", Integer.toString(playerData.getInt("coins")))
                ));
            }
        }, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
