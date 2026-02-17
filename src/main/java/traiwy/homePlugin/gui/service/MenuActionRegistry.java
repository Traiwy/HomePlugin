package traiwy.homePlugin.gui.service;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.menu.ListMenu;

public class MenuActionRegistry {
    private final MenuService service;
    private final MenuManager menuManager;

    public MenuActionRegistry(MenuService service, MenuManager menuManager) {
        this.service = service;
        this.menuManager = menuManager;
    }

    public void execute(String action, Player player) {
        switch (action) {
            case "open_list" -> menuManager.openMenu(player, service.getListMenu(), "list");
            case "open_delete" -> menuManager.openMenu(player, service.getDeleteMenu(), "delete");
            case "open_settings" -> menuManager.openMenu(player, service.getSettingsMenu(), "settings");
            case "create_home" -> {
                player.performCommand("home create");
                menuManager.clear(player);
                player.closeInventory();
            }
            case "redstone" -> menuManager.openMenu(player, service.getDeleteAllMenu(), "delete");
            case "back" -> menuManager.openPrevious(player);
        }
    }
}