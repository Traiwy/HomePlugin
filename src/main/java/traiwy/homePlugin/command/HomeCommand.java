package traiwy.homePlugin.command;

import traiwy.homePlugin.command.impl.AcceptCommand;
import traiwy.homePlugin.command.impl.CancelCommand;
import traiwy.homePlugin.command.impl.CreateCommand;
import traiwy.homePlugin.command.impl.MenuCommand;
import traiwy.homePlugin.gui.menu.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class HomeCommand implements CommandExecutor, TabExecutor {
    private final Map<String, SubCommand> commands = new HashMap<>();

    public HomeCommand(MainMenu mainMenu) {
        commands.put("menu", new MenuCommand(mainMenu));
        commands.put("accept", new AcceptCommand());
        commands.put("cancel", new CancelCommand());
        commands.put("create", new CreateCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if(args.length == 0) return false;

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = commands.get(subCommandName);

        if (subCommand == null) {
            player.sendMessage("Неизвестная подкоманда: " + subCommandName);
            return true;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        subCommand.execute(player, subArgs);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String[] SUBCOMMAND = command.getAliases().toArray(new String[0]);
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

    //public boolean setHomeCommand(Player player) {
    //    UUID uuid = player.getUniqueId();
    //    if (PlayerChatListener.awaitingHomeName.containsKey(uuid)) {
    //        player.sendMessage("Вы уже вводите название дома");
    //        return true;
    //    }
    //    PlayerChatListener.readyToSetHome.add(uuid);
    //    BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
    //        if (!PlayerChatListener.readyToSetHome.contains(uuid)) {
    //            PlayerChatListener.awaitingHomeName.get(uuid);
    //            PlayerChatListener.awaitingHomeName.remove(uuid);
    //            return;
    //        }
    //        for (int i = 0; i < 10; i++) {
    //            player.sendMessage(" ");
    //        }
    //        player.sendMessage("Введите название дома в чат...");
    //    }, 0L, 20L);
    //    PlayerChatListener.awaitingHomeName.put(uuid, task.toString());
    //    return true;
    //}
//
    //public static String getSharedPlayerName() {
    //    return sharedPlayerName;
    //}
}
