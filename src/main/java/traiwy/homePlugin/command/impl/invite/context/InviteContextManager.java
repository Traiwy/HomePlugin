package traiwy.homePlugin.command.impl.invite.context;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InviteContextManager {
    private final Map<UUID, InviteContext> contexts = new HashMap<>();

    public void create(Player sender, Player target) {
        contexts.put(sender.getUniqueId(), new InviteContext(sender, target));
    }

    public InviteContext get(Player sender) {
        return contexts.get(sender.getUniqueId());
    }

    public void remove(Player sender) {
        contexts.remove(sender.getUniqueId());
    }

    public boolean has(Player sender) {
        return contexts.containsKey(sender.getUniqueId());
    }
}
