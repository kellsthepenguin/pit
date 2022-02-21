package xyz.kellsthepenguin.pit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xyz.kellsthepenguin.pit.Pit.playingStatuses;

public class PitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            String uuid = player.getUniqueId().toString();

            playingStatuses.put(uuid, true);
        } else {
            sender.sendMessage("You need to execute command as user.");
        }
        return true;
    }
}
