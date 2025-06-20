package event;

import invHolderMainMenu.delayHolder.DelayHolder;
import invHolderMainMenu.deleteHolder.DeleteMenuHolder;
import invHolderMainMenu.favoritesHolder.FavoritesHomeMenuHolder;
import invHolderMainMenu.favoritesHolder.choiseFovoritesHoolder.ChoiseFavoiritesHomeMenuHolder;
import invHolderMainMenu.homeHolder.HomeHolder;
import invHolderMainMenu.listHomeHolder.ListHomeHolder;
import invHolderMainMenu.settingHolder.SettingsHomeHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;


public class CanselClickInventory implements Listener {
    @EventHandler
    public void  ClickInventoryEvent(InventoryClickEvent event) {
       if (!(event.getWhoClicked() instanceof Player) || event.getClickedInventory() == null) return;

        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof ListHomeHolder
                || holder instanceof SettingsHomeHolder
                || holder instanceof HomeHolder
                || holder instanceof FavoritesHomeMenuHolder
                || holder instanceof ChoiseFavoiritesHomeMenuHolder
                || holder instanceof DeleteMenuHolder
                || holder instanceof DelayHolder) {
            event.setCancelled(true);
        }
    }

}
