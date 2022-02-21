package xyz.kellsthepenguin.pit.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static xyz.kellsthepenguin.pit.Pit.configuration;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player target = e.getEntity();
        Player killer = e.getEntity().getKiller();

        if (killer != null) {
            String message = configuration.getString("message")
                    .replaceAll("@t", target.getName())
                    .replaceAll("@k", killer.getName());

            e.setDeathMessage(message);
        }
    }
}

