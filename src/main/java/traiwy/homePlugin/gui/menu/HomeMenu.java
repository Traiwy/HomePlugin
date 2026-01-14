package traiwy.homePlugin.gui.menu;

import lombok.AllArgsConstructor;
import traiwy.homePlugin.config.data.ConfigData;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;
import traiwy.homePlugin.gui.button.action.CommandAction;
import traiwy.homePlugin.gui.button.action.OpenMenuAction;
import traiwy.homePlugin.util.ItemFactory;

public class HomeMenu extends Menu {
    public  HomeMenu(ConfigData configData, Menu listMenu, Menu settingsMenu) {
        super("homemenu", "Main menu", 27);

        var mainMenuItems = configData.menu().get("mainmenu").items();

        addButton(11, new MenuButton(
                ItemFactory.create(mainMenuItems.get("elytra")),
                new CommandAction("create")
        ));

        addButton(13, new MenuButton(
                ItemFactory.create(mainMenuItems.get("apple")),
                new OpenMenuAction(listMenu)
        ));

        addButton(15, new MenuButton(
                ItemFactory.create(mainMenuItems.get("diamond")),
                new OpenMenuAction(settingsMenu)
        ));
    }
}

