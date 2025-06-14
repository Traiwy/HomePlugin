package invHolderMainMenu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class CustomMenuHolder implements InventoryHolder, HomeInventoryHolder {
    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }
}
