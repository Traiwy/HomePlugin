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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class ClickDeleteHomeEvent implements Listener {
    Set<UUID> awaitingClickDeleteHome = new HashSet<>();
    @EventHandler
    public void DeleteHomeEvent(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        UUID target = player.getUniqueId();
        Inventory clickInv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();


        if (clickInv == null) return;

        if (clickInv.getHolder() instanceof ListHomeHolder) {
            if (item != null && item.getType() == Material.LIME_DYE) {
                awaitingClickDeleteHome.add(target);
            }
            return;
        }

        if (clickInv.getHolder() instanceof DeleteMenuHolder && awaitingClickDeleteHome.contains(target)) {

            if (event.isRightClick() && event.getClick().isShiftClick()){
                player.sendMessage("Вы нажали шифт");
                if(item != null && item.getType() == Material.PLAYER_HEAD) {


                    String homeName = getHomeNameFromItem(item);
                    if (homeName != null && !homeName.isEmpty()) {
                        player.performCommand("delhome " + homeName);
                        awaitingClickDeleteHome.remove(target);
                        clickInv.removeItem(item);
                        player.updateInventory();
                        player.closeInventory();
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