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
            case "open_list" -> menuManager.openMenu(player, service.getListMenu());
            case "open_delete" -> menuManager.openMenu(player, service.getDeleteMenu());
            case "open_settings" -> menuManager.openMenu(player, service.getSettingsMenu());
            case "create_home" -> {
                player.performCommand("home create");
                menuManager.clear(player);
                player.closeInventory();
            }
            case "redstone" -> service.getHomeFacade().deleteHomes(player.getName());
            case "back" -> menuManager.openPrevious(player);
            case "invisibility_potion" -> menuManager.openMenu(player, service.getEffectMenu());
            case "speed" -> menuManager.openMenu(player, service.getEffectMenu());
            case "strength" -> menuManager.openMenu(player, service.getEffectMenu());
            case "regeneration" -> menuManager.openMenu(player, service.getEffectMenu());
            case "resistance" -> menuManager.openMenu(player, service.getEffectMenu());
            case "fire_resistance" -> menuManager.openMenu(player, service.getEffectMenu());
            case "night_vision" -> menuManager.openMenu(player, service.getEffectMenu());
            case "jump_boost" -> menuManager.openMenu(player, service.getEffectMenu());
        }
    }
}