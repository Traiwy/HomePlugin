package command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import util.HomeManager;

public class SethomeCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    public  SethomeCommand(JavaPlugin plugin, HomeManager homeManager){
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
        Location loc = player.getLocation();
        String nameHome = (args.length > 0) ? args[0] : "default";
        homeManager.SetHome(player, nameHome, loc);
        player.sendMessage("§aДом §e'" + nameHome + "' §aуспешно сохранён!");
        return true;


    }
}

