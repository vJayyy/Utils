package me.adaptified.utils.command;

import me.adaptified.utils.PlayerData;
import static me.adaptified.utils.PlayerData.isMuted;
import me.adaptified.utils.Utils;
import me.adaptified.utils.banning.Ban;
import me.adaptified.utils.banning.BanType;
import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(source = SourceType.ANY, permission = "utils.mute")
public class Command_mute extends BukkitCommand<Utils> {

    @Override
    public boolean run(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "/mute <player>");
            return true;
        }

        final Player player = getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "That player is not online!");
            return true;
        }

        if (plugin.MutedPlayers.contains(player)) {
            sender.sendMessage(ChatColor.RED + "That player is already muted! Try /unmute.");
            return true;
        }

        if (!plugin.MutedPlayers.contains(player)) {

            Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " has just muted " + player.getName());

            player.sendMessage(ChatColor.RED + "You were muted by " + sender.getName());
            plugin.MutedPlayers.add(player);
            plugin.MutedPlayers.save();
        }
        return true;
    }

}
