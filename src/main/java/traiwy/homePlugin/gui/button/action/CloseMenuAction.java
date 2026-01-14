package traiwy.homePlugin.gui.button.action;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.button.ButtonAction;

public class CloseMenuAction implements ButtonAction {
    @Override
    public void execute(Player player) {
        player.closeInventory();
    }
}
