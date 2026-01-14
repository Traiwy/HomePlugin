package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.util.HomeManager;

import java.util.*;

@AllArgsConstructor
public class PlayerChatListener implements Listener {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    private final Set<UUID> awaitingHomeName  = new HashSet<>();

    public void startHomeNaming(Player player) {
        awaitingHomeName.add(player.getUniqueId());
        player.sendMessage("Введите название дома в чат...");
    }
    @EventHandler
    public void ChatListener(AsyncPlayerChatEvent  event){
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();


        if(!awaitingHomeName.contains(uuid))return;
        event.setCancelled(true);

        String nameHome = event.getMessage().trim();
        if(nameHome.isEmpty()){
            player.sendMessage("Название точки не может быть пустым");
            return;
        }

        Bukkit.getScheduler().runTask(plugin, () -> {
            final Location loc = player.getLocation();
            homeManager.setHome(player.getName(), nameHome, loc, player.getName());
            awaitingHomeName.remove(uuid);
        });
    }

}
