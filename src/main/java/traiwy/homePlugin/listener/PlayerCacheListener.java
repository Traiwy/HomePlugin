package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.CacheHome;
import traiwy.homePlugin.db.home.MySqlHomeRepository;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.home.Home;

import java.util.List;

@AllArgsConstructor
public class PlayerCacheListener implements Listener {
    private final CacheHome cache;
    private final MySqlHomeRepository mySqlHomeRepository;
    private final MenuManager menuManager;
    private final JavaPlugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        cache.removeAllHome(player.getName());

        mySqlHomeRepository.findByOwner(player.getName()).thenAccept(homes -> {
            Bukkit.getScheduler().runTask(plugin, () -> {
                for (Home home : homes) {
                    cache.add(player.getName(), home);
                }
                Bukkit.getLogger().info("Дома игрока загружены: " + player.getName());
            });
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        List<Home> homes = cache.getAllHome(player.getName());

        if (homes.isEmpty()) return;

        mySqlHomeRepository.replaceAll(player.getName(), homes)
                .thenRun(() -> Bukkit.getLogger().info(
                        "Дома игрока сохранены: " + player.getName()
                ));

        cache.removeAllHome(player.getName());
        menuManager.clear(player);
    }
}
