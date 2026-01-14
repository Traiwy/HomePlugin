package traiwy.homePlugin.listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerMessageListener implements Listener {
    @EventHandler
    public void onMessageSharePlayer(AsyncChatEvent e){
        Player player = e.getPlayer();
        String input = PlainTextComponentSerializer.plainText().serialize(e.message());


    }
}
