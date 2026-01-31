package traiwy.homePlugin.command.impl;

import lombok.AllArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.command.SubCommand;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.menu.MainMenu;
import traiwy.homePlugin.gui.service.MenuService;

@AllArgsConstructor
public class MenuCommand implements SubCommand {
    private final MenuService menuService;
    private final MenuManager menuManager;

    @Override
    public @NotNull String getName() {
        return "";
    }

    @Override
    public @NotNull String getDescription() {
        return "";
    }

    @Override
    public @NotNull String permission() {
        return "";
    }

    @Override
    public void execute(@NotNull CommandSender sender, @NotNull String[] args) {
        Player player = (Player) sender;
       menuManager.openMenu(player, menuService.getMainMenu());
    }
}
