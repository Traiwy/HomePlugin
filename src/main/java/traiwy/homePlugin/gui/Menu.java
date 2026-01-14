package traiwy.homePlugin.gui;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import traiwy.homePlugin.gui.button.MenuButton;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Menu implements InventoryHolder {
    private final String id;
    private final Inventory inventory;
    private final Map<Integer, MenuButton> buttons = new HashMap<>();

    public Menu(String id, String title, int size) {
        this.id = id;
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void open(Player player) {
        updateInventory();
        player.openInventory(inventory);
    }

    public void handleClick(int slot, Player player) {
        MenuButton button = buttons.get(slot);
        if (button != null) {
            button.click(player);
        }
    }

    public void updateInventory() {
        inventory.clear();
        buttons.forEach((slot, button) -> inventory.setItem(slot, button.item()));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void addButton(int slot, MenuButton button) {
        buttons.put(slot, button);
        inventory.setItem(slot, button.item());
    }

}
