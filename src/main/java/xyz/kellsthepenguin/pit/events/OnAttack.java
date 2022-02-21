package xyz.kellsthepenguin.pit.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Map;

import static xyz.kellsthepenguin.pit.Pit.playingStatuses;

public class OnAttack implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAttack(EntityDamageByEntityEvent e) {
        Entity damagerEntity = e.getDamager();
        Entity damagedEntity = e.getEntity();

        for (Map.Entry<String, Boolean> entrySet : playingStatuses.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }

        if (!(damagerEntity instanceof Player damager
                && damagedEntity instanceof Player damaged
                && playingStatuses.containsKey(damager.getUniqueId().toString())
                && playingStatuses.containsKey(damaged.getUniqueId().toString())
                && playingStatuses.get(damager.getUniqueId().toString())
                && playingStatuses.get(damaged.getUniqueId().toString())
        )) {
            e.setCancelled(true);
        }
    }
}