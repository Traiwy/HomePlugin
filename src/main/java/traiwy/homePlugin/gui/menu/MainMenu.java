package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class MainMenu extends Menu {
    public static final int[] GRAY_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18,19,20,21,22,23,24,25,26};
    private final MenuService service;


    public MainMenu(MenuService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void setup(Player player) {


        for (int j : GRAY_PANEL) {
            setItem(j, new MenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }

    }
}

