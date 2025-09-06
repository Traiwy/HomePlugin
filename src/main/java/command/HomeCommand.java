package command;

import event.PlayerChatListener;
import invHolderMainMenu.homeHolder.MainMenuHomeBuilder;
import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import invHolderMainMenu.shareHolder.ShareHomeMenuBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.HomeManager;

import java.util.*;
import java.util.stream.Collectors;

public class HomeCommand implements CommandExecutor, TabExecutor {
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    private final MainMenuHomeBuilder mainMenuHomeBuilder;
    private final SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    private final ListHomeMenuBuilder listHomeMenuBuilder;
    private final ShareHomeMenuBuilder shareHomeMenuBuilder;

    private static String sharedPlayerName;

    public HomeCommand(JavaPlugin plugin, HomeManager homeManager, MainMenuHomeBuilder mainMenuHomeBuilder, SettingsHomeMenuBuilder settingsHomeMenuBuilder, ListHomeMenuBuilder listHomeMenuBuilder, ShareHomeMenuBuilder shareHomeMenuBuilder) {
        this.plugin = plugin;
        this.homeManager = homeManager;
        this.mainMenuHomeBuilder = mainMenuHomeBuilder;
        this.settingsHomeMenuBuilder = settingsHomeMenuBuilder;
        this.listHomeMenuBuilder = listHomeMenuBuilder;
        this.shareHomeMenuBuilder = shareHomeMenuBuilder;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (args.length == 0) {
            return false;
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
                if (args.length < 2) {
                    player.sendMessage("Введите корректную команду: /home delete <название точки дома>");
                    return true;
                }
                String homeName = args[1].toLowerCase();
                Set<String> homes = homeManager.getHomeNames(player);
                if ((homeManager.getHome(player, homeName)) != null) {
                    homeManager.deleteHome(player, homeName);
                    player.sendMessage("Вы удалили точку дома: " + homeName);
                } else {
                    player.sendMessage("Введите корректное название точки дома");
                }
                return true;
            case "settings":
                settingsHomeMenuBuilder.getSettingsGUI(player);
                break;
            case "set":
                setHomeCommand(player);
                break;
            case "share":
                String namePlayer = args[1].toLowerCase();
                sharedPlayerName = namePlayer;
                Player sharePlayer = Bukkit.getPlayer(args[1]);
                if(namePlayer != null && player.getName().equalsIgnoreCase(namePlayer)){
                    player.sendMessage("Ошибка: Вы не можете отправить точку дома самому себе." );
                    return true;
                }
                if (args.length < 2) {
                    player.sendMessage("Введите корректную команду: /home share <ник игрока>");
                    return true;
                }
                if (sharePlayer == null) {
                    player.sendMessage("Такого игрока нет на сервере");
                    return true;
                }
                shareHomeMenuBuilder.getShareHomeMenu(player);

                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] SUBCOMMAND = {"menu", "list", "delete", "settings", "set", "share"};
        if (strings.length == 1) {
            return Arrays.stream(SUBCOMMAND)
                    .filter(sub -> sub.startsWith(strings[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (strings.length == 2 && strings[0].equalsIgnoreCase("share")) {
            List<String> players = Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .collect(Collectors.toList());

            return players.stream()
                    .filter(name -> name.toLowerCase().startsWith(strings[1].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public boolean setHomeCommand(Player player) {
        UUID uuid = player.getUniqueId();
        if (PlayerChatListener.awaitingHomeName.containsKey(uuid)) {
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
            for (int i = 0; i < 10; i++) {
                player.sendMessage(" ");
            }
            player.sendMessage("Введите название дома в чат...");
        }, 0L, 20L);
        PlayerChatListener.awaitingHomeName.put(uuid, task.toString());
        return true;
    }

    public static String getSharedPlayerName() {
        return sharedPlayerName;
    }
}
