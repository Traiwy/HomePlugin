package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import util.HomeManager;

import java.util.Set;

public class DeleteHomeCommand implements CommandExecutor {
    private final HomeManager homeManager;
    public DeleteHomeCommand(HomeManager homeManager){
        this.homeManager = homeManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        String homeName = args[0].toLowerCase();

        Set<String> homes = homeManager.getHomeNames(player);
        if(!homes.contains(homeName)){
            player.sendMessage("Такого дома не существует");
            return true;
        }

        homeManager.deleteHome(player, homeName);
        player.sendMessage("§aДом '" + homeName + "' успешно удалён!");
        return true;
    }
}
