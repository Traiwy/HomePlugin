package event;

import invHolderMainMenu.deleteHolder.DeleteHomeMenu;
import invHolderMainMenu.deleteHolder.DeleteMenuHolder;
import invHolderMainMenu.listHomeHolder.ListHomeHolder;
import invHolderMainMenu.listHomeHolder.ListHomeMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.DeleteMapManager;

import java.util.*;


public class ClickDeleteHomeEvent implements Listener {
    private final DeleteMapManager deleteMapManager;
    public ClickDeleteHomeEvent(DeleteMapManager deleteMapManager){
        this.deleteMapManager = deleteMapManager;
    }
    @EventHandler
    public void DeleteHomeEvent(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        UUID target = player.getUniqueId();
        Inventory clickInv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();


        if (clickInv == null) return;

        if (clickInv.getHolder() instanceof ListHomeHolder) {
            if (item != null && item.getType() == Material.LIME_DYE) {
               deleteMapManager.addAwaitingClickDeleteHome(player);
            }
            return;
        }

        if (clickInv.getHolder() instanceof DeleteMenuHolder && deleteMapManager.containsAwaitingClickDeleteHome(player)) {
            event.setCancelled(true);
            if (event.isRightClick() && event.getClick().isShiftClick()){
                player.sendMessage("Вы нажали шифт");
                if(item != null && item.getType() == Material.PLAYER_HEAD) {


                    String homeName = getHomeNameFromItem(item);
                    if (homeName != null && !homeName.isEmpty()) {
                        player.performCommand("delhome " + homeName);
                        clickInv.removeItem(item);
                        player.updateInventory();
                    } else {
                        player.sendMessage("§cНе удалось определить название дома!");
                    }
                }
            }

        }
    }

    private String getHomeNameFromItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;

        ItemMeta meta = item.getItemMeta();
        if (meta.hasDisplayName()) {
            return meta.getDisplayName().replace("§", "").trim();
        }
        return null;
    }
}