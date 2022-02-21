package xyz.kellsthepenguin.pit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static xyz.kellsthepenguin.pit.Pit.playingStatuses;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();

        playingStatuses.put(uuid, false);
        e.getPlayer().sendMessage(Boolean.toString(playingStatuses.containsKey(uuid)));
    }
}
