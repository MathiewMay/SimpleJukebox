package ca.cobiy.simple_jukebox.commands;

import ca.cobiy.simple_jukebox.SimpleJukebox;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SimpleJukeboxCMD implements CommandExecutor {

    SimpleJukebox plugin;
    public SimpleJukeboxCMD(SimpleJukebox plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1 && args[0].equals("reload") && sender.hasPermission("SimpleJukebox.reload")){
            plugin.reloadConfig();
            sender.sendMessage(plugin.configurator.msgSJReloaded());
            return true;
        }else if(args.length != 1) {
            String output = plugin.configurator.msgSJBadSyntax(); if(output.contains("{CMD}")) output.replace("{CMD}", "/"+label);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', output));
            return true;
        }else {
            sender.sendMessage(plugin.configurator.msgNoPerms());
            return true;
        }
    }
}
