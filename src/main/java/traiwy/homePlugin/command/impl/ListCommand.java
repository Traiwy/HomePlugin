package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.provider.CommandErrorMessageProvider;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.service.MenuService;

@AllArgsConstructor
public class ListCommand implements SubCommand {
    private final MenuService menuService;
    private final MenuManager menuManager;
    @Override
    public @NotNull String getName() {
        return "";
    }

    @Override
    public @NotNull String permission() {
        return "";
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.CONSOLE)
            );
            return;
        }

        if (!permission().isEmpty() && !player.hasPermission(permission())) {
            player.sendMessage(
                    CommandErrorMessageProvider.getMessage(CommandError.NO_PERMISSION)
            );
            return;
        }

        menuManager.openMenu(player, menuService.getListMenu(), "list");
    }
}
