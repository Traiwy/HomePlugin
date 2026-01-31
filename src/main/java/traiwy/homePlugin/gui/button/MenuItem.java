package traiwy.homePlugin.gui.button;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public record MenuItem(ItemStack item, Consumer<InventoryClickEvent> action) {
    public void click(InventoryClickEvent event) {
        if (action != null) action.accept(event);
    }
}
