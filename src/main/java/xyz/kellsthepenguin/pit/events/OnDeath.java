package xyz.kellsthepenguin.pit.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static xyz.kellsthepenguin.pit.Pit.*;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) throws IOException {
        Player target = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if (killer != null && target.getWorld().getName().equals(configuration.getString("world"))) {
            String message = configuration.getString("message")
                    .replaceAll("@t", target.getName())
                    .replaceAll("@k", killer.getName());

            File killerDataFile = Paths.get(userDataPath.toString(), String.format("%s.yml", killer.getUniqueId())).toFile();
            File targetDataFile = Paths.get(userDataPath.toString(), String.format("%s.yml", target.getUniqueId())).toFile();
            FileConfiguration killerData = YamlConfiguration.loadConfiguration(killerDataFile);
            FileConfiguration targetData = YamlConfiguration.loadConfiguration(targetDataFile);

            killerData.set("kills", killerData.getInt("kills") + 1);
            targetData.set("deaths", targetData.getInt("deaths") + 1);
            killerData.save(killerDataFile);
            targetData.save(targetDataFile);

            playingStatuses.put(target.getUniqueId().toString(), false);

            e.setDeathMessage(message);
        }
    }
}
