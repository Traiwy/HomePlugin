package traiwy.homePlugin.gui.button.action;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.ButtonAction;

import java.awt.event.ActionEvent;

public record OpenMenuAction(Menu target) implements ButtonAction {

    @Override
    public void execute(Player player) {
        target.open(player);
    }
}
