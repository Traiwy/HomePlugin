package traiwy.homePlugin.gui.service;

import lombok.Getter;
import traiwy.homePlugin.cache.CacheHome;
import traiwy.homePlugin.configuration.dto.ConfigData;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.menu.*;

@Getter
public class MenuService {
    private final MainMenu mainMenu;
    private final ListMenu listMenu;
    private final DeleteMenu deleteMenu;
    private final SettingsMenu settingsMenu;
    private final DelayMenu delayMenu;
    private final ShareMenu shareMenu;

    private final ConfigData cfgData;
    private final CacheHome cacheHome;
    private final MenuActionRegistry menuActionRegistry;
    private final MenuManager menuManager;

    public MenuService(CacheHome cacheHome, ConfigData cfgData) {
        this.cacheHome = cacheHome;
        this.menuManager = new MenuManager();
        this.menuActionRegistry = new MenuActionRegistry(this, menuManager);
        this.cfgData = cfgData;
        this.mainMenu = new MainMenu(this);
        this.listMenu = new ListMenu(this);
        this.deleteMenu = new DeleteMenu(this);
        this.settingsMenu = new  SettingsMenu();
        this.delayMenu = new DelayMenu();
        this.shareMenu = new ShareMenu();
    }
}
