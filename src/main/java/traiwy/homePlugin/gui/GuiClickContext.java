package traiwy.homePlugin.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;


public record GuiClickContext(Player player, int slot, ClickType clickType) {
}
