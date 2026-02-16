package traiwy.homePlugin.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Menu implements InventoryHolder {
    private final MenuService menuService;
    private Inventory inventory;
    private final Map<Integer, MenuItem> items = new HashMap<>();

    protected Menu(MenuService menuService) {
        this.menuService = menuService;
    }

    public abstract void setup(Player player);

    public void open(@NotNull Player player, @NotNull String nameMenu) {
        inventory = menuService.getCfgData().getConfiguration().menus().get(nameMenu).build();
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
