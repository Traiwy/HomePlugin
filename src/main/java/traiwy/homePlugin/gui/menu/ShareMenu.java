package traiwy.homePlugin.gui.menu;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.service.MenuService;

public class ShareMenu extends Menu {
    private final MenuService menuService;
    public ShareMenu(MenuService menuService) {
        super(menuService);
        this.menuService = menuService;
    }

    @Override
    public void setup(Player player) {

    }
}
