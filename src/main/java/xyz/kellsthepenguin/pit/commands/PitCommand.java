package xyz.kellsthepenguin.pit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static xyz.kellsthepenguin.pit.Pit.configuration;
import static xyz.kellsthepenguin.pit.Pit.playingStatuses;

public class PitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 0 && player.getWorld().getName().equals(configuration.getString("world"))) {
                String uuid = player.getUniqueId().toString();
                String kit = args[0];
                ConfigurationSection kitsSection = configuration.getConfigurationSection("kits");

                if (kitsSection.contains(kit)) {
                    ConfigurationSection kitSection = kitsSection.getConfigurationSection(kit);
                    assert false;
                    player.getInventory().setArmorContents(((List<ItemStack>) kitSection.get("armor")).toArray(ItemStack[]::new));
                    player.getInventory().setStorageContents(((List<ItemStack>) kitSection.get("storage")).toArray(ItemStack[]::new));
                    playingStatuses.put(uuid, true);
                } else {
                    sender.sendMessage(ChatColor.RED + "That kit is not exist!");
                }

                playingStatuses.put(uuid, true);
            }
        } else {
            sender.sendMessage("You need to execute command as user.");
        }
        return true;
    }
}
