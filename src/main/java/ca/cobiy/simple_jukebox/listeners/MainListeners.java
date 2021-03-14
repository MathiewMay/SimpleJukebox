package ca.cobiy.simple_jukebox.listeners;

import ca.cobiy.simple_jukebox.SimpleJukebox;
import ca.cobiy.simple_jukebox.guis.JukeboxGUI;
import ca.cobiy.simple_jukebox.utils.ItemChecker;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MainListeners implements Listener {

    public HashMap<Player, Inventory> playerJukebox = new HashMap<>();
    public HashMap<Player, Block> playerJukeboxBlock = new HashMap<>();
    public HashMap<Block, Sound> playingJukebox = new HashMap<>();

    SimpleJukebox plugin;
    public MainListeners(SimpleJukebox plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void openJukebox(PlayerInteractEvent event){
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getHand() != null && event.getClickedBlock() != null && event.getHand().equals(EquipmentSlot.HAND) && event.getClickedBlock().getType().equals(Material.JUKEBOX)){
            event.setCancelled(true);
            Player player = event.getPlayer();
            if(plugin.configurator.requiresPerms() && !player.hasPermission("SimpleJukebox.use")){
                player.sendMessage(plugin.configurator.msgNoPermsUse()); return;
            }
            Inventory jukebox = plugin.gui.inv();
            playerJukebox.put(player, jukebox);
            playerJukeboxBlock.put(player, event.getClickedBlock());
            player.openInventory(jukebox);
        }
    }

    @EventHandler
    public void closeJukebox(InventoryCloseEvent event){
        if(event.getPlayer() instanceof Player){
            Player player = (Player) event.getPlayer();
            if(playerJukebox.containsKey(player) && event.getInventory().equals(playerJukebox.get(player))){
                playerJukebox.remove(player);
                playerJukeboxBlock.remove(player);
            }
        }
    }

    @EventHandler
    public void chooseDisc(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(event.getClickedInventory() != null && playerJukebox.containsKey(player) && event.getClickedInventory().equals(playerJukebox.get(player)) && event.getInventory().getItem(event.getSlot()) != null){
                event.setCancelled(true);
                Block block = playerJukeboxBlock.get(player);
                if(event.getSlot() == event.getInventory().getSize()-1){
                    if(playingJukebox.containsKey(block)){ stopPlayingJukebox(block, player); }
                }else{
                    boolean adj = false;
                    if(playingJukebox.containsKey(block)){ stopPlayingJukebox(block, null); adj = true; }
                    startPlayingJukebox(block, event.getInventory().getItem(event.getSlot()), player, adj);
                    playingJukebox.put(block, Sound.valueOf(event.getInventory().getItem(event.getSlot()).getType().toString()));
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    public void griefPreventJukebox(InventoryDragEvent event){
        if(event.getWhoClicked() instanceof Player){
            Player player = (Player) event.getWhoClicked();
            if(playerJukebox.containsKey(player) && event.getView().getTopInventory().equals(playerJukebox.get(player))){
                for(int slot : event.getRawSlots()) {
                    if(slot >= 0 && slot < event.getInventory().getSize()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void breakJukebox(BlockBreakEvent event){
        Block block = event.getBlock();
        if(playingJukebox.containsKey(block)){
            stopPlayingJukebox(block, event.getPlayer());
        }
    }

    private void startPlayingJukebox(Block block, ItemStack itemStack, Player dj, boolean announceDJ){
        int radius = plugin.configurator.radius();
        for(Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), radius, radius, radius)){
            if(entity instanceof Player){
                Player target = ((Player) entity).getPlayer();
                assert target != null;
                target.playSound(block.getLocation(), Sound.valueOf(itemStack.getType().toString()), 1f, 1f);
                if(announceDJ)
                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText((plugin.configurator.msgTrackChanged()+" "+plugin.configurator.msgNowPlaying()+" "+ ItemChecker.materialToAuthor(itemStack.getType())).replace("{PLAYER}", dj.getDisplayName())));
                else
                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText((plugin.configurator.msgTrackStarted()+" "+plugin.configurator.msgNowPlaying()+" "+ ItemChecker.materialToAuthor(itemStack.getType())).replace("{PLAYER}", dj.getDisplayName())));
                playingJukebox.remove(block);
            }
        }
    }

    public void stopPlayingJukebox(Block block, Player player){
        int radius = plugin.configurator.radius();
        for(Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), radius, radius, radius)){
            if(entity instanceof Player){
                Player target = ((Player) entity).getPlayer();
                assert target != null;
                target.stopSound(playingJukebox.get(block));
                playingJukebox.remove(block);
                if(player != null)
                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(plugin.configurator.msgTrackStopped().replace("{PLAYER}", player.getDisplayName())));
            }
        }
    }

}
