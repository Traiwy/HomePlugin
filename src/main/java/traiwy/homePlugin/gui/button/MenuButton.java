package traiwy.homePlugin.gui.button;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public record MenuButton(ItemStack item, ButtonAction action) {
    public void click(Player player) {
        action.execute(player);
    }
}
