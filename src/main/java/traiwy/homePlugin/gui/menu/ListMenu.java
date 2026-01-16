package traiwy.homePlugin.gui.menu;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.cache.home.CacheHome;
import traiwy.homePlugin.config.data.ConfigData;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;
import traiwy.homePlugin.gui.button.action.OpenMenuAction;
import traiwy.homePlugin.util.ItemFactory;


public class ListMenu extends Menu {
    public static final int[] GRAY_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53};

    private final CacheHome cacheHome;
    public ListMenu(ConfigData configData, Menu settingsMenu, CacheHome cacheHome) {
        super("listmenu", "List Home", 54);
        this.cacheHome = cacheHome;

        var listMenuItems = configData.menu().get(getId()).items();

        getButtons().put(45, new MenuButton(
                ItemFactory.create(listMenuItems.get("arrow")),
                null
        ));

        getButtons().put(49, new MenuButton(
                ItemFactory.create(listMenuItems.get("limedye")),
                null
        ));

        getButtons().put(4, new MenuButton(
                ItemFactory.create(listMenuItems.get("nether_star")),
                new OpenMenuAction(settingsMenu)
        ));

        for(int i = 0; i < GRAY_PANEL.length; i++){
            addButton(GRAY_PANEL[i], new MenuButton(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }
    }
}
