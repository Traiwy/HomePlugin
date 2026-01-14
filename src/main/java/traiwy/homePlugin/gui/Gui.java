package traiwy.homePlugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface Gui extends InventoryHolder {
    Inventory createInventory(Player player);
    void openMenu(Player player);
    void handleClick(GuiClickContext  event);
}
