package traiwy.homePlugin.gui.menu.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class SettingsMenu extends Menu{
    public static final int[] GRAY_PANEL = {0,9,18,27,36,8,17,26,35,44,53,46,47,48,49,50,51,52};
    private final MenuService service;

    public SettingsMenu(MenuService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void setup(Player player) {
        for(int j : GRAY_PANEL) {
            setItem(j, new MenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));


        }
    }

}
