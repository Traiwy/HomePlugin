package traiwy.homePlugin.command.impl.invite.context;

import org.bukkit.entity.Player;

public record InviteContext(Player sender, Player receiver) {
}
