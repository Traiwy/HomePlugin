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
    private final Map<UUID, MenuHistory> histories = new HashMap<>();

    private MenuHistory history(Player player) {
        return histories.computeIfAbsent(
                player.getUniqueId(),
                k -> new MenuHistory()
        );
    }

    public void openMenu(Player player, Menu menu) {
        history(player).push(menu);
        menu.open(player);
    }

    public void openPrevious(Player player) {
        MenuHistory history = history(player);

        history.pop();
        Menu previous = history.peek();

        if (previous != null) {
            previous.open(player);
        } else {
            player.closeInventory();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        MenuHistory history = histories.get(player.getUniqueId());
        if (history == null || history.isEmpty()) return;

        Menu current = history.peek();
        if (!event.getInventory().equals(current.getInventory())) return;

        event.setCancelled(true);
        current.onClick(event);
    }


    public void clear(Player player) {
        histories.remove(player.getUniqueId());
    }
}