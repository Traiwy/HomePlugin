package traiwy.homePlugin.gui.menu.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class DeleteAllMenu extends Menu {
    private final MenuService service;
    public DeleteAllMenu( MenuService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void setup(Player player) {

    }
}
