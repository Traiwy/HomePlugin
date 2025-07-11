package command;

import event.PlayerChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import util.HomeManager;

import java.util.UUID;

public class SethomeCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    public  SethomeCommand(JavaPlugin plugin, HomeManager homeManager){
        this.plugin = plugin;
        this.homeManager = homeManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if(PlayerChatListener.awaitingHomeName.containsKey(uuid)){
            player.sendMessage("Вы уже вводите название дома");
            return true;
        }
        PlayerChatListener.readyToSetHome.add(uuid);
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (!PlayerChatListener.readyToSetHome.contains(uuid)) {
                PlayerChatListener.awaitingHomeName.get(uuid);
                PlayerChatListener.awaitingHomeName.remove(uuid);
                return;
            }
            for(int i = 0; i<10;i++){
                player.sendMessage(" ");
            }
            player.sendMessage("Введите название дома в чат...");
        }, 0L, 20L);
        PlayerChatListener.awaitingHomeName.put(uuid, task.toString());
        return true;

    }
}

