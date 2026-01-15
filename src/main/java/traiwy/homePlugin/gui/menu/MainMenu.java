package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.config.data.ConfigData;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;
import traiwy.homePlugin.gui.button.action.CommandAction;
import traiwy.homePlugin.gui.button.action.OpenMenuAction;
import traiwy.homePlugin.util.ItemFactory;

public class MainMenu extends Menu {
    public static final int[] GRAY_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18,19,20,21,22,23,24,25,26};
    public MainMenu(ConfigData configData, Menu listMenu, Menu settingsMenu) {
        super("meinnmenu", "Main menu", 27);

        var mainMenuItems = configData.menu().get("mainmenu").items();

        addButton(11, new MenuButton(
                ItemFactory.create(mainMenuItems.get("elytra")),
                new CommandAction("home create")
        ));

        addButton(13, new MenuButton(
                ItemFactory.create(mainMenuItems.get("apple")),
                new OpenMenuAction(listMenu)
        ));

        addButton(15, new MenuButton(
                ItemFactory.create(mainMenuItems.get("diamond")),
                new OpenMenuAction(settingsMenu)
        ));

        for(int i = 0; i < GRAY_PANEL.length; i++){
            addButton(GRAY_PANEL[i], new MenuButton(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }
    }
}

