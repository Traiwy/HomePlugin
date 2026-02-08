package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.facade.HomeFacade;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.home.Home;

import java.util.List;

@AllArgsConstructor
public class PlayerCacheListener implements Listener {
    private final HomeCache cache;
    private final HomeFacade homeFacade;
    private final MenuManager menuManager;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        cache.removeAllHome(player.getName());

        homeFacade.loadHomesForPlayer(player);

        Bukkit.getLogger().info("Загрузка домов игрока инициирована: " + player.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        List<Home> homes = cache.getAllHome(player.getName());

        if (homes.isEmpty()) return;

        homeFacade.saveHomesForPlayer(player);

        cache.removeAllHome(player.getName());
        menuManager.clear(player);
        Bukkit.getLogger().info("Сохранение домов игрока инициировано: " + player.getName());
    }
}
