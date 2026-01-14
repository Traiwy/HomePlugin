package traiwy.homePlugin.listener;

import traiwy.homePlugin.gui.delayHolder.DelayHolder;
import traiwy.homePlugin.gui.deleteHolder.DeleteMenuHolder;
import traiwy.homePlugin.gui.homeHolder.HomeHolder;
import traiwy.homePlugin.gui.listHomeHolder.ListHomeHolder;
import traiwy.homePlugin.gui.settingHolder.SettingsHomeHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;


public class CanselClickInventoryListener implements Listener {
    @EventHandler
    public void  ClickInventoryEvent(InventoryClickEvent event) {
       if (!(event.getWhoClicked() instanceof Player) || event.getClickedInventory() == null) return;

        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof ListHomeHolder
                || holder instanceof SettingsHomeHolder
                || holder instanceof HomeHolder
                || holder instanceof DeleteMenuHolder
                || holder instanceof DelayHolder) {
            event.setCancelled(true);
        }
    }

}
