package traiwy.homePlugin.gui;

import org.bukkit.inventory.Inventory;
import traiwy.homePlugin.configuration.IconConfiguration;

import java.util.Map;

public class BuiltMenu {

    private final Inventory inventory;
    private final Map<Integer, IconConfiguration> slotIcons;

    public BuiltMenu(Inventory inventory, Map<Integer, IconConfiguration> slotIcons) {
        this.inventory = inventory;
        this.slotIcons = slotIcons;
    }

    public Inventory inventory() {
        return inventory;
    }

    public IconConfiguration getIcon(int slot) {
        return slotIcons.get(slot);
    }

    public Map<Integer, IconConfiguration> slotIcons() {
        return slotIcons;
    }
}