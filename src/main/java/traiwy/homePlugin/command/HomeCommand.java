package traiwy.homePlugin.command;

import traiwy.homePlugin.command.impl.*;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.menu.MainMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.listener.PlayerChatListener;
import traiwy.homePlugin.manager.RequestManager;

import java.util.*;
import java.util.stream.Collectors;

public class HomeCommand implements CommandExecutor, TabExecutor {
    private final Map<String, SubCommand> commands = new HashMap<>();

    public HomeCommand(MenuService menuService, MenuManager menuManager, PlayerChatListener playerChatListener, RequestManager requestManager) {
        commands.put("menu", new MenuCommand(menuService, menuManager));
        commands.put("accept", new AcceptCommand());
        commands.put("cancel", new CancelCommand());
        commands.put("create", new CreateCommand(playerChatListener));
        commands.put("invite", new InviteCommand(requestManager));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.ARGS)
            );
            return true;
        }

        String subCommandName = args[0].toLowerCase();
        SubCommand subCommand = commands.get(subCommandName);

        if (subCommand == null) {
            sender.sendMessage(
                    CommandErrorMessageProvider.getMessage(
                            CommandError.UNKNOWN_SUBCOMMAND
                    )
            );
            return true;
        }

        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        subCommand.execute(sender, subArgs);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 1) {
            return commands.keySet().stream()
                    .filter(sub -> sub.startsWith(strings[0].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();

    }

}
