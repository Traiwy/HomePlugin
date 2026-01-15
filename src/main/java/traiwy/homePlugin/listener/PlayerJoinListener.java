package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import traiwy.homePlugin.cache.home.CacheHome;
import traiwy.homePlugin.db.MySqlRepository;
import traiwy.homePlugin.home.Home;

@AllArgsConstructor
public class PlayerJoinListener implements Listener {
    private final CacheHome cache;
    private final MySqlRepository storage;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        storage.getHomes(player.getName()).thenAccept(homes -> {
            for(Home home : homes) {
                cache.add(player.getName(), home);
            }
        });

        player.sendMessage("Дома игрока загружены");
    }
}
