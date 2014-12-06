package org.nationsmc.calebbfnv;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Tim [calebbfmv] (12/6/2014) for CommandBlockUtil
 */
public class CommandBlockUtil extends JavaPlugin implements CommandExecutor {

    private static CommandBlockUtil instance;

    public static CommandBlockUtil getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be a player!");
            return true;
        }
        final Player player = (Player) sender;
        player.sendMessage(ChatColor.RED + "Opening a CommandBlock GUI!");
        player.sendMessage(ChatColor.GOLD + "Type \"finish\" to close to GUI");
        new BukkitRunnable() {
            @Override
            public void run() {
                new ExampleGUI(player);
            }
        }.runTaskLater(this, 10L);
        return true;
    }

}
