package xyz.kellsthepenguin.pit.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static xyz.kellsthepenguin.pit.Pit.playingStatuses;
import static xyz.kellsthepenguin.pit.Pit.userDataPath;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();

        File userDataFile = Paths.get(userDataPath.toString(), String.format("%s.yml", uuid)).toFile();

        if (!userDataFile.exists()) {
            userDataFile.createNewFile();
            FileConfiguration userData = YamlConfiguration.loadConfiguration(userDataFile);

            userData.set("coin", 0);
            userData.set("kills", 0);
            userData.set("deaths", 0);
            userData.save(userDataFile);
        }

        playingStatuses.put(uuid, false);
        e.getPlayer().sendMessage(Boolean.toString(playingStatuses.containsKey(uuid)));
    }
}
