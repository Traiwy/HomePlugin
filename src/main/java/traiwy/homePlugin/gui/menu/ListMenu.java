package traiwy.homePlugin.gui.menu;

import traiwy.homePlugin.config.data.ConfigData;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;
import traiwy.homePlugin.gui.button.action.CommandAction;
import traiwy.homePlugin.gui.button.action.OpenMenuAction;
import traiwy.homePlugin.util.ItemFactory;

public class ListMenu extends Menu {
    public ListMenu(ConfigData configData, Menu settingsMenu) {
        super("listmenu", "List Home", 54);

        var listMenuItems = configData.menu().get(getId()).items();

        getButtons().put(45, new MenuButton(
                ItemFactory.create(listMenuItems.get("arrow")),
                new CommandAction("back")
        ));

        getButtons().put(49, new MenuButton(
                ItemFactory.create(listMenuItems.get("limedye")),
                null
        ));

        getButtons().put(4, new MenuButton(
                ItemFactory.create(listMenuItems.get("nether_star")),
                new OpenMenuAction(settingsMenu)
        ));
    }
}
