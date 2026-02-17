package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class MainMenu extends Menu {
    private final MenuService service;


    public MainMenu(MenuService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void setup(Player player) {

    }
}

