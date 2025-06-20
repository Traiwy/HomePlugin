package event;

import invHolderMainMenu.listHomeHolder.ListHomeHolder;
import invHolderMainMenu.settingHolder.SettingsHomeHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class CanselClickInventory implements Listener {
    @EventHandler
    public void  ClickInventoryEvent(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof  Player)) return;
        Player player = (Player) event.getWhoClicked(); //Игрок
        Inventory inventory = event.getClickedInventory(); //Инвентарь
        if(event.getInventory().getHolder() instanceof ListHomeHolder) {
                event.setCancelled(true);
        }
        if(event.getInventory().getHolder() instanceof SettingsHomeHolder){
            event.setCancelled(true);
        }
        ItemStack item = event.getCurrentItem(); //Предмет
        if (inventory == null) return;
        if(item == null || item.getType() == Material.AIR) return;
    }

}
