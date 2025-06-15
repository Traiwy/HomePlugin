package event;

import invHolderMainMenu.listHomeHolder.ListHomeHolder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.HomeManager;


public class ClickTeleportHome implements Listener {
    private final HomeManager homeManager;
    public ClickTeleportHome(HomeManager homeManager){
        this.homeManager = homeManager;
    }
    @EventHandler
    public void TeleportHome(InventoryClickEvent event){
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        Inventory clickInv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        if(clickInv.getHolder() instanceof ListHomeHolder){
            if(item != null && item.getType() == Material.PLAYER_HEAD){
                ItemMeta meta = item.getItemMeta();
                if (!meta.hasDisplayName()) {
                    player.sendMessage("§cУ этого дома нет названия!");
                    return;
                }
                String homeName = meta.getDisplayName();
                Location location = homeManager.getHome(player, homeName);
                if(location == null){
                    player.sendMessage("Дом не найден");
                }
                player.teleport(location);
                player.sendMessage("Вы телепортированны в дом" + homeName);
                player.closeInventory();
            }
        }
    }
}
