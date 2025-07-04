package command;

import invHolderMainMenu.homeHolder.MainMenuHomeBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MenuHomeCommand implements CommandExecutor {
    private static MainMenuHomeBuilder mainMenuHomeBuilder;
    public MenuHomeCommand(MainMenuHomeBuilder mainMenuHomeBuilder){
        this.mainMenuHomeBuilder = mainMenuHomeBuilder;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return  false;

        Player player = (Player) sender;
        mainMenuHomeBuilder.HomeGUI(player);
        return  true;
    }
}

