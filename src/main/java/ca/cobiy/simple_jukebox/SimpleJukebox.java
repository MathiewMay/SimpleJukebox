package ca.cobiy.simple_jukebox;

import ca.cobiy.simple_jukebox.commands.SimpleJukeboxCMD;
import ca.cobiy.simple_jukebox.guis.JukeboxGUI;
import ca.cobiy.simple_jukebox.listeners.MainListeners;
import ca.cobiy.simple_jukebox.utils.Configurator;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class SimpleJukebox extends JavaPlugin {

    MainListeners mainListeners;
    public Configurator configurator;
    public JukeboxGUI gui;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configurator = new Configurator(this);
        gui = new JukeboxGUI(this);
        this.getCommand("SimpleJukebox").setExecutor(new SimpleJukeboxCMD(this));
        mainListeners = new MainListeners(this);
        Bukkit.getPluginManager().registerEvents(mainListeners, this);
    }

    @Override
    public void onDisable() {
        for (Map.Entry<Player, Inventory> playerInventoryEntry : mainListeners.playerJukebox.entrySet()) {
            playerInventoryEntry.getKey().closeInventory();
        }
        for (Map.Entry<Block, Sound> playingJukeboxEntry : mainListeners.playingJukebox.entrySet()) {
            mainListeners.stopPlayingJukebox(playingJukeboxEntry.getKey(), null);
        }
    }
}
