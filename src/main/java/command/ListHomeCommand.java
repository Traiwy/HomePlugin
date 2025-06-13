package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import util.SaveLocationPlayer;

public class ListHomeCommand implements CommandExecutor {
    private final JavaPlugin plugin;
    private final SaveLocationPlayer saveLocationPlayer;
    public  ListHomeCommand(JavaPlugin plugin, SaveLocationPlayer saveLocationPlayer){
        this.plugin = plugin;
        this.saveLocationPlayer = saveLocationPlayer;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Отправьте эту команду на сервере");
            return false;
        }
        Player player = (Player) sender;
        saveLocationPlayer.ListLocationPlayer(player);
        return true;
    }
}
