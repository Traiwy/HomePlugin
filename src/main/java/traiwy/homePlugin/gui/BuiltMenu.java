package traiwy.homePlugin.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.Inventory;
import traiwy.homePlugin.configuration.IconConfiguration;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class BuiltMenu {
    private final Inventory inventory;
    private final Map<Integer, IconConfiguration> slotIcons;
    private final List<Integer> dynamicSlots;
}