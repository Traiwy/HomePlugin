package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import traiwy.homePlugin.HomePlugin;
import traiwy.homePlugin.cache.home.CacheHome;
import traiwy.homePlugin.db.MySqlRepository;
import traiwy.homePlugin.home.Home;

import java.util.List;

@AllArgsConstructor
public class PlayerLeaveListener implements Listener {
    private final MySqlRepository mySqlRepository;
    private final CacheHome cacheHome;

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        final Player player = e.getPlayer();
        List<Home> homes = cacheHome.getAllHome(player.getName());
        if(homes.isEmpty()) return;
        for(Home home : homes){
            mySqlRepository.addHome(home);
            System.out.println("Все дома игрока: " + player.getName() + " сохранены!");
        }
    }

}
