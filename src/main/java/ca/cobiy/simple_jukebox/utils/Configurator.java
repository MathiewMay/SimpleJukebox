package ca.cobiy.simple_jukebox.utils;

import ca.cobiy.simple_jukebox.SimpleJukebox;
import net.md_5.bungee.api.ChatColor;

public class Configurator {

    SimpleJukebox plugin;
    public Configurator(SimpleJukebox plugin){
        this.plugin = plugin;
    }

    public int radius(){
        int returned = 30;
        if(plugin.getConfig().contains("JukeboxRadius")) returned = plugin.getConfig().getInt("JukeboxRadius");
        return returned;
    }

    public boolean requiresPerms(){
        boolean returned = false;
        if(plugin.getConfig().contains("JukeboxRequiresPermission")) returned = plugin.getConfig().getBoolean("JukeboxRequiresPermission");
        return  returned;
    }

    public String msgNoPerms(){
        String returned = "&cYou are not allowed to use this command.";
        if(plugin.getConfig().contains("Messages.NoPermission")) returned = plugin.getConfig().getString("Messages.NoPermission");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgNoPermsUse(){
        String returned = "&cYou are not allowed to use jukeboxes.";
        if(plugin.getConfig().contains("Messages.NoPermissionUse")) returned = plugin.getConfig().getString("Messages.NoPermissionUse");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgSJBadSyntax(){
        String returned = "&cBad syntax when using '{CMD}', type '/help SimpleJukebox' for help.";
        if(plugin.getConfig().contains("Messages.SJBadSyntax")) returned = plugin.getConfig().getString("Messages.SJBadSyntax");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgSJReloaded(){
        String returned = "&2Reloaded config files";
        if(plugin.getConfig().contains("Messages.SJReloaded")) returned = plugin.getConfig().getString("Messages.SJReloaded");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgNowPlaying(){
        String returned = "Now Playing:";
        if(plugin.getConfig().contains("Messages.JukeboxNowPlaying")) returned = plugin.getConfig().getString("Messages.JukeboxNowPlaying");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgTrackStarted(){
        String returned = "{PLAYER} Started the Track,";
        if(plugin.getConfig().contains("Messages.JukeboxTrackStarted")) returned = plugin.getConfig().getString("Messages.JukeboxTrackStarted");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgTrackChanged(){
        String returned = "{PLAYER} Changed the Track,";
        if(plugin.getConfig().contains("Messages.JukeboxTrackChanged")) returned = plugin.getConfig().getString("Messages.JukeboxTrackChanged");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgTrackStopped(){
        String returned = "{PLAYER} Stopped the Track.";
        if(plugin.getConfig().contains("Messages.JukeboxTrackStopped")) returned = plugin.getConfig().getString("Messages.JukeboxTrackStopped");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

    public String msgStopTrack(){
        String returned = "&cStop Track";
        if(plugin.getConfig().contains("Messages.StopTrack")) returned = plugin.getConfig().getString("Messages.StopTrack");
        return ChatColor.translateAlternateColorCodes('&', returned);
    }

}
