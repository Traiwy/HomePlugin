package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import util.HomeManager;

import java.util.Set;

public class ListHomeCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    public  ListHomeCommand(JavaPlugin plugin, HomeManager homeManager){
        this.plugin = plugin;
        this.homeManager = homeManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Отправьте эту команду на сервере");
            return false;
        }
        Player player = (Player) sender;
        Set<String> homePlayer = homeManager.getHomeNames(player);
        if(homePlayer.isEmpty()){
            player.sendMessage("У вас нет сохраненных домов");
            return true;
        }

        player.sendMessage("Ваши дома: ");
        for(String name : homePlayer){
            if (homeManager.getHome(player, name) != null){
                double x = homeManager.getHome(player, name).getBlockX();
                double y = homeManager.getHome(player, name).getBlockY();
                double z = homeManager.getHome(player, name).getBlockZ();
                player.sendMessage("§e" + name + "§f: x=" + x + ", y=" + y + ", z=" + z);
            }
        }
        return true;
    }
}
