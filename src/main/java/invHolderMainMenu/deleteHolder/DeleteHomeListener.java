package invHolderMainMenu.deleteHolder;

import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.DeleteMapManager;

public class DeleteHomeListener implements Listener {
   private final DeleteMapManager deleteMapManager;
    private final ListHomeMenuBuilder listHomeMenuBuilder;

    public DeleteHomeListener(DeleteMapManager deleteMapManager, ListHomeMenuBuilder listHomeMenuBuilder) {
        this.deleteMapManager = deleteMapManager;
        this.listHomeMenuBuilder = listHomeMenuBuilder;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Проверка, что клик сделал игрок
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();

        // Проверка на null для инвентаря и предмета
        if (inventory == null || item == null || item.getType() == Material.AIR) return;

        // Обработка клика по LIME_DYE
        if (item.getType() == Material.LIME_DYE) {
            deleteMapManager.addAwaitingClickDeleteHome(player);
            return;
        }

        // Проверка, что инвентарь принадлежит DeleteMenuHolder
        if (inventory.getHolder() instanceof DeleteMenuHolder && deleteMapManager.containsAwaitingClickDeleteHome(player)) {
            event.setCancelled(true); // Отмена действия по умолчанию
            if(item.getType() == Material.RED_DYE) listHomeMenuBuilder.ListHomeGUI(player);
            // Проверка на шифт + правый клик
            if (event.isRightClick() && event.isShiftClick()) {
                if (item.getType() == Material.PLAYER_HEAD) {
                    String homeName = getHomeNameFromItem(item);
                    if (homeName != null && !homeName.isEmpty()) {
                        player.performCommand("delhome " + homeName);
                        inventory.remove(item); // Удаляем предмет из инвентаря
                        player.updateInventory();
                        player.sendMessage("§aДом '" + homeName + "' успешно удален!");
                    } else {
                        player.sendMessage("§cНе удалось определить название дома!");
                    }
                }
            }
        }
    }
    private String getHomeNameFromItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {
            // Удаляем цветовые коды и лишние пробелы
            return meta.getDisplayName().replaceAll("§[0-9a-fk-or]", "").trim();
        }
        return null;
    }
}