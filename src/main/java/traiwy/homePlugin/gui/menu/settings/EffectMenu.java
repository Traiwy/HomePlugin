package traiwy.homePlugin.gui.menu.settings;

import org.bukkit.entity.Player;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.service.MenuService;

public class EffectMenu extends Menu {
    private final MenuService menuService;
    protected EffectMenu(MenuService menuService) {
        super(menuService);
        this.menuService = menuService;
    }

    @Override
    public void setup(Player player) {

    }
}
