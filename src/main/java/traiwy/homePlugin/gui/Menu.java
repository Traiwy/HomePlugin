package traiwy.homePlugin.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import traiwy.homePlugin.gui.button.MenuItem;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Menu implements InventoryHolder {
    private final String id;
    private Inventory inventory;
    private final int size;
    private final String title;
    private final Map<Integer, MenuItem> items = new HashMap<>();

    protected Menu(String id, String title, int size) {
        this.id = id;
        this.size = size;
        this.title = title;
    }

    public abstract void setup(Player player);

    public void open(Player player) {
        if (inventory == null) {
            inventory = Bukkit.createInventory(this, size, title);
        } else {
            inventory.clear();
        }
        items.clear();
        setup(player);
        player.openInventory(inventory);
    }


    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        final MenuItem item = items.get(event.getRawSlot());
        if(item != null) item.click(event);
    }


    public void setItem(int slot, MenuItem menuItem) {
        items.put(slot, menuItem);
        inventory.setItem(slot, menuItem.item());
    }


}
