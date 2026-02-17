package traiwy.homePlugin.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.configuration.IconConfiguration;
import traiwy.homePlugin.configuration.MenuConfiguration;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class Menu implements InventoryHolder {
    private final MenuService menuService;
    private Inventory inventory;
    protected Map<Integer, IconConfiguration> slotIcons = new HashMap<>();
    protected Map<Integer, MenuItem> items = new HashMap<>();


    protected Menu(MenuService menuService) {
        this.menuService = menuService;
    }

    public abstract void setup(Player player);

    public void open(@NotNull Player player, @NotNull String nameMenu) {

        MenuConfiguration config = menuService.getCfgData()
                .getConfiguration()
                .menus()
                .get(nameMenu);

        if (config == null) {
            player.sendMessage("§cMenu not found: " + nameMenu);
            return;
        }

        BuiltMenu built = config.build();

        this.inventory = built.inventory();
        this.slotIcons = built.slotIcons();

        this.items.clear();
        setup(player);

        player.openInventory(inventory);
    }

    public void open(@NotNull Player player) {
        if (inventory == null) return;
        items.clear();
        setup(player);
        player.openInventory(inventory);
    }

    public void onClick(InventoryClickEvent event) {

        event.setCancelled(true);

        int slot = event.getRawSlot();
        if (slot < 0 || slot >= inventory.getSize()) return;

        Player player = (Player) event.getWhoClicked();

        MenuItem item = items.get(slot);
        if (item != null) {
            item.click(event);
            return;
        }
        IconConfiguration icon = slotIcons.get(slot);
        if (icon == null) return;

        String action = icon.action();
        if (action == null || action.isEmpty()) return;

        menuService.getMenuActionRegistry().execute(action, player);
    }


    public void setItem(int slot, MenuItem menuItem) {
        items.put(slot, menuItem);
        inventory.setItem(slot, menuItem.item());
    }


}
