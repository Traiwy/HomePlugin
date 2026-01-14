package traiwy.homePlugin.gui.button.action;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.button.ButtonAction;

import java.awt.event.ActionListener;

public record CommandAction(String command) implements ButtonAction{

    @Override
    public void execute(Player player) {
        player.performCommand(this.command);
    }
}
