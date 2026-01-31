package traiwy.homePlugin.gui.service;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.MenuManager;

public class MenuActionRegistry {
    private final MenuService service;
    private final MenuManager menuManager;

    public MenuActionRegistry(MenuService service, MenuManager menuManager) {
        this.service = service;
        this.menuManager = menuManager;
    }

    public void execute(String action, Player player) {
        switch (action) {
            case "open_list" -> menuManager.openMenu(player, service.getListMenu());
            case "open_settings" -> menuManager.openMenu(player, service.getSettingsMenu());
            case "create_home" -> player.performCommand("home create");
        }
    }
}
