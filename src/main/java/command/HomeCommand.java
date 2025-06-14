package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import util.HomeManager;

public class HomeCommand implements CommandExecutor {
    private final HomeManager homeManager;
    public  HomeCommand(HomeManager homeManager){
        this.homeManager = homeManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if(args.length == 0){
            player.sendMessage("Вы не указали название дома");
        }
        String nameHome = args[0];
        homeManager.getHome(player, nameHome);
        return true;
    }
}
