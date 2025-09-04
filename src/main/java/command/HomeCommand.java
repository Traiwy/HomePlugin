package command;

import invHolderMainMenu.homeHolder.MainMenuHomeBuilder;
import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.HomeManager;

import java.util.*;
import java.util.stream.Collectors;

public class HomeCommand implements CommandExecutor, TabExecutor {
    private final HomeManager homeManager;
    private final MainMenuHomeBuilder mainMenuHomeBuilder;
    private final SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    private final ListHomeMenuBuilder listHomeMenuBuilder;
    public  HomeCommand(HomeManager homeManager, MainMenuHomeBuilder mainMenuHomeBuilder, SettingsHomeMenuBuilder settingsHomeMenuBuilder, ListHomeMenuBuilder listHomeMenuBuilder){
        this.homeManager = homeManager;
        this.mainMenuHomeBuilder = mainMenuHomeBuilder;
        this.settingsHomeMenuBuilder = settingsHomeMenuBuilder;
        this.listHomeMenuBuilder = listHomeMenuBuilder;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("Вы не указали название дома");
        }
        String subcommand = args[0].toLowerCase();
        switch (subcommand) {
            case "menu":
                mainMenuHomeBuilder.HomeGUI(player);
                break;
            case "list":
                listHomeMenuBuilder.ListHomeGUI(player);
                break;
            case "delete":
                if(args.length < 2){
                    player.sendMessage("Введите корректную команду: /home delete <название точки дома>");
                    return true;
                }
                String homeName = args[1].toLowerCase();
                Set<String> homes = homeManager.getHomeNames(player);
                if((homeManager.getHome(player, homeName)) != null){
                     homeManager.deleteHome(player, homeName);
                     player.sendMessage("Вы удалили точку дома: " + homeName);
                }else{
                    player.sendMessage("Введите корректное название точки дома");
                }
                return true;
            case "settings":
                settingsHomeMenuBuilder.SettingsGUI(player);
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] SUBCOMMAND = {"menu", "list", "delete", "settings"};
        if(strings.length == 1){
            return Arrays.stream(SUBCOMMAND)
                    .filter(sub -> sub.startsWith(strings[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
