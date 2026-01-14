package traiwy.homePlugin.gui.deleteHolder;

import traiwy.homePlugin.gui.listHomeHolder.ListHomeMenuBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import traiwy.homePlugin.util.DeleteMapManager;
import traiwy.homePlugin.util.HomeManager;

public class DeleteHomeListener implements Listener {
    //private final DeleteMapManager deleteMapManager;
    //private final ListHomeMenuBuilder listHomeMenuBuilder;
    //private final HomeManager homeManager;
//
    //public DeleteHomeListener(DeleteMapManager deleteMapManager, ListHomeMenuBuilder listHomeMenuBuilder, HomeManager homeManager) {
    //    this.deleteMapManager = deleteMapManager;
    //    this.listHomeMenuBuilder = listHomeMenuBuilder;
    //    this.homeManager = homeManager;
    //}
//
    //@EventHandler
    //public void onInventoryClick(InventoryClickEvent event) {
    //    if (!(event.getWhoClicked() instanceof Player)) return;
//
    //    Player player = (Player) event.getWhoClicked();
    //    Inventory inventory = event.getClickedInventory();
    //    ItemStack item = event.getCurrentItem();
    //    if (inventory == null || item == null || item.getType() == Material.AIR) return;
//
//
    //    if (item.getType() == Material.LIME_DYE) {
    //        deleteMapManager.addAwaitingClickDeleteHome(player);
    //        return;
    //    }
//
    //    if (inventory.getHolder() instanceof DeleteMenuHolder && deleteMapManager.containsAwaitingClickDeleteHome(player)) {
    //        event.setCancelled(true);
    //        if (item.getType() == Material.RED_DYE) listHomeMenuBuilder.ListHomeGUI(player);
    //        if (event.isShiftClick() && event.isRightClick()) {
//
    //            if (item.getType() == Material.PLAYER_HEAD) {
    //                String homeName = getHomeNameFromItem(item);
    //                if (homeManager.isOwner(player, player.getName(), homeName) && homeName != null && !homeName.isEmpty()) {
    //                    player.performCommand("home delete " + homeName);
    //                    inventory.remove(item);
    //                    player.updateInventory();
    //                    player.sendMessage("§aДом '" + homeName + "' успешно удален!");
    //                } else {
    //                    player.sendMessage("Вы не являетесь владельцем точки дома");
    //                }
    //            } else {
    //                player.sendMessage("§cНе удалось определить название дома!");
    //            }
    //        }
    //    }
    //}
    //private String getHomeNameFromItem(ItemStack item) {
    //    if (item == null || !item.hasItemMeta()) {
    //        return null;
    //    }
    //    ItemMeta meta = item.getItemMeta();
    //    if (meta != null && meta.hasDisplayName()) {
    //        return meta.getDisplayName().replaceAll("§[0-9a-fk-or]", "").trim();
    //    }
    //    return null;
    //}
}