package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import traiwy.homePlugin.cache.CacheHome;
import traiwy.homePlugin.db.home.MySqlHomeRepository;
import traiwy.homePlugin.home.Home;

import java.util.List;

@AllArgsConstructor
public class PlayerCacheListener implements Listener {
    private final CacheHome cache;
    private final MySqlHomeRepository mySqlHomeRepository;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        mySqlHomeRepository.findByOwner(player.getName()).thenAccept(homes -> {
            for(Home home : homes) {
                cache.add(player.getName(), home);
            }
        });

        player.sendMessage("Дома игрока загружены");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        final Player player = e.getPlayer();
        List<Home> homes = cache.getAllHome(player.getName());
        if(homes.isEmpty()) return;
        for(Home home : homes){
            mySqlHomeRepository.save(home);
            System.out.println(home.homeName());
        }
        cache.remove(player.getName());
        System.out.println("Все дома игрока: " + player.getName() + " сохранены!");

    }
}
