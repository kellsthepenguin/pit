package xyz.kellsthepenguin.pit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.IOException;
import java.nio.file.Paths;

import static xyz.kellsthepenguin.pit.Pit.configuration;
import static xyz.kellsthepenguin.pit.Pit.dataPath;

public class KitCreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 0 && player.hasPermission("pit.commands.kitcreate")) {
                PlayerInventory inventory = player.getInventory();
                ItemStack[] armor = inventory.getArmorContents();
                ItemStack[] storage = inventory.getStorageContents();

                ConfigurationSection kitsSection = configuration.getConfigurationSection("kits");
                ConfigurationSection kitSection = kitsSection.createSection(args[0]);

                kitSection.set("armor", armor);
                kitSection.set("storage", storage);

                try {
                    configuration.save(Paths.get(dataPath, "config.yml").toFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            sender.sendMessage("You need to execute command as user.");
        }
        return true;
    }
}
