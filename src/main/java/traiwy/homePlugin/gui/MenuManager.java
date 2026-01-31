package traiwy.homePlugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MenuManager implements Listener {
    private final Map<UUID, Menu> openedMenus = new HashMap<>();

    public void openMenu(Player player, Menu menu) {
        menu.open(player);
        openedMenus.put(player.getUniqueId(), menu);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) return;

        final Player player = (Player) event.getWhoClicked();
        final Menu menu = openedMenus.get(player.getUniqueId());

        if(menu != null && menu.getInventory().equals(menu.getInventory())) menu.onClick(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        openedMenus.remove(event.getPlayer().getUniqueId());
    }
}
