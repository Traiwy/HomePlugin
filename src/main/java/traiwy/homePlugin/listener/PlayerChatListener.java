package traiwy.homePlugin.listener;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.facade.HomeFacade;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Location;
import traiwy.homePlugin.home.Member;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
public class PlayerChatListener implements Listener {
    private final JavaPlugin plugin;
    private final HomeFacade homeFacade;

    private final Set<UUID> awaitingHomeName =
            Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final Map<UUID, Integer> reminderTasks = new ConcurrentHashMap<>();

    public void startHomeNaming(Player player) {
        UUID uuid = player.getUniqueId();

        if (awaitingHomeName.contains(uuid)) return;

        awaitingHomeName.add(uuid);
        player.sendMessage("§eВведите название дома в чат...");

        int taskId = Bukkit.getScheduler().runTaskTimer(
                plugin,
                () -> {
                    if (!player.isOnline() || !awaitingHomeName.contains(uuid)) {
                        stopReminder(uuid);
                        return;
                    }
                    player.sendMessage("§7Введите название дома в чат...");
                },
                40L,
                60L
        ).getTaskId();

        reminderTasks.put(uuid, taskId);
    }

    private void stopReminder(UUID uuid) {
        Integer taskId = reminderTasks.remove(uuid);
        if (taskId != null) {
            Bukkit.getScheduler().cancelTask(taskId);
        }
        awaitingHomeName.remove(uuid);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!awaitingHomeName.contains(uuid)) return;

        event.setCancelled(true);

        String nameHome = event.getMessage().trim();
        if (nameHome.isEmpty()) {
            Bukkit.getScheduler().runTask(plugin,
                    () -> player.sendMessage("§cНазвание не может быть пустым"));
            return;
        }

        stopReminder(uuid);

        Bukkit.getScheduler().runTask(plugin, () -> {
            Location loc = new Location(
                    player.getWorld().getName(),
                    player.getX(), player.getY(), player.getZ(),
                    player.getYaw(), player.getPitch()
            );

            homeFacade.createHome(player, nameHome, loc)
                    .thenAccept(home ->
                            player.sendMessage("§aДом '" + home.homeName() + "' сохранён!"))
                    .exceptionally(ex -> {
                        player.sendMessage("§cОшибка сохранения дома");
                        ex.printStackTrace();
                        return null;
                    });


        });
    }

}
