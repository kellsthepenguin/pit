package xyz.kellsthepenguin.pit.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static xyz.kellsthepenguin.pit.Pit.playingStatuses;

public class OnLeavePit implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();

        playingStatuses.remove(uuid);
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        String uuid = e.getPlayer().getUniqueId().toString();

        playingStatuses.remove(uuid);
    }
}
