package event;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import util.HomeManager;

import java.util.*;

public class PlayerChatListener implements Listener {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    public static Map<UUID, String> awaitingHomeName   = new HashMap();
    public static Set<UUID> readyToSetHome = new HashSet<>();
    public PlayerChatListener(JavaPlugin plugin, HomeManager homeManager){
        this.plugin = plugin;
        this.homeManager = homeManager;
    }

    @EventHandler
    public void ChatListener(AsyncPlayerChatEvent  event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if(!readyToSetHome.contains(uuid))return;
        String nameHome = event.getMessage();

        Bukkit.getScheduler().runTask(plugin, ()->{
            Location loc = player.getLocation();
            homeManager.SetHome(player, nameHome, loc);
            player.sendMessage("Дом с названием  " + nameHome +" установлен");
        });
        Bukkit.getScheduler().runTask(plugin, () -> {
            awaitingHomeName.remove(uuid);
            readyToSetHome.remove(uuid);
        });
        event.setCancelled(true);

    }

}
