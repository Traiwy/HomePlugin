package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.ErrorService;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.service.MenuService;

@AllArgsConstructor
public class MenuCommand implements SubCommand {
    private final MenuService menuService;
    private final MenuManager menuManager;
    private final ErrorService errorService;

    @Override
    public @NotNull String getName() {
        return "menu";
    }

    @Override
    public @NotNull String permission() {
        return "";
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(
                    errorService.getCommandErrorMessageProvider().getMessage(CommandError.CONSOLE)
            );
            return;
        }

        if (!permission().isEmpty() && !player.hasPermission(permission())) {
            player.sendMessage(
                    errorService.getCommandErrorMessageProvider().getMessage(CommandError.NO_PERMISSION)
            );
            return;
        }

        menuManager.openMenu(player, menuService.getMainMenu(), "main");
    }
}
