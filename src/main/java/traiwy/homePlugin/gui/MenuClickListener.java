package traiwy.homePlugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuClickListener implements Listener{
    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player player)) return;
        if(event.getInventory().getHolder() instanceof Menu menu){
            event.setCancelled(true);
            menu.handleClick(event.getSlot(), player);
        }
    }
}
