package ca.cobiy.simple_jukebox.guis;

import ca.cobiy.simple_jukebox.SimpleJukebox;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class JukeboxGUI {

    SimpleJukebox plugin;
    public JukeboxGUI(SimpleJukebox plugin){
        this.plugin = plugin;
    }

    public Inventory inv(){
        Inventory inventory = Bukkit.createInventory(null, 9*2, "Jukebox");
        for(ItemStack disc : getDiscs()){
            inventory.addItem(disc);
        }
        ItemStack stop = new ItemStack(Material.REDSTONE);
        ItemMeta stopMeta = stop.getItemMeta();
        assert stopMeta != null;
        stopMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.configurator.msgStopTrack()));
        stop.setItemMeta(stopMeta);
        inventory.setItem(inventory.getSize()-1, stop);
        return inventory;
    }

    private ArrayList<ItemStack> getDiscs(){
        ArrayList<ItemStack> discs = new ArrayList<>();
        discs.add(new ItemStack(Material.MUSIC_DISC_13));
        discs.add(new ItemStack(Material.MUSIC_DISC_CAT));
        discs.add(new ItemStack(Material.MUSIC_DISC_BLOCKS));
        discs.add(new ItemStack(Material.MUSIC_DISC_CHIRP));
        discs.add(new ItemStack(Material.MUSIC_DISC_FAR));
        discs.add(new ItemStack(Material.MUSIC_DISC_MALL));
        discs.add(new ItemStack(Material.MUSIC_DISC_MELLOHI));
        discs.add(new ItemStack(Material.MUSIC_DISC_STAL));
        discs.add(new ItemStack(Material.MUSIC_DISC_STRAD));
        discs.add(new ItemStack(Material.MUSIC_DISC_WARD));
        discs.add(new ItemStack(Material.MUSIC_DISC_11));
        discs.add(new ItemStack(Material.MUSIC_DISC_WAIT));
        discs.add(new ItemStack(Material.MUSIC_DISC_PIGSTEP));
        return discs;
    }
}
