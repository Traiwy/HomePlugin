package traiwy.homePlugin.gui.service;

import lombok.Getter;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.command.impl.invite.context.InviteContextManager;
import traiwy.homePlugin.configuration.Configuration;
import traiwy.homePlugin.facade.HomeFacade;
import traiwy.homePlugin.gui.MainMenu;
import traiwy.homePlugin.gui.MenuManager;
import traiwy.homePlugin.gui.menu.*;
import traiwy.homePlugin.gui.menu.choose.ChooseHomeMenu;
import traiwy.homePlugin.gui.menu.settings.DeleteAllMenu;
import traiwy.homePlugin.gui.menu.settings.SettingsMenu;
import traiwy.homePlugin.gui.menu.settings.DelayMenu;
import traiwy.homePlugin.manager.ClickHomeManager;
import traiwy.homePlugin.manager.RequestManager;

@Getter
public class MenuService {
    private final MainMenu mainMenu;
    private final ListMenu listMenu;
    private final DeleteMenu deleteMenu;
    private final SettingsMenu settingsMenu;
    private final DelayMenu delayMenu;
    private final ShareMenu shareMenu;
    private final DeleteAllMenu deleteAllMenu;
    private final ChooseHomeMenu chooseHomeMenu;

    private final Configuration cfgData;
    private final HomeCache homeCache;
    private final MenuActionRegistry menuActionRegistry;
    private final MenuManager menuManager;
    private final ClickHomeManager clickHomeManager;
    private final RequestManager requestManager;
    private final InviteContextManager inviteContextManager;
    private final HomeFacade homeFacade;

    public MenuService(HomeCache homeCache, Configuration cfgData, HomeFacade homeFacade) {
        this.homeCache = homeCache;
        this.homeFacade = homeFacade;
        this.requestManager = new RequestManager();
        this.inviteContextManager = new InviteContextManager();
        this.clickHomeManager = new ClickHomeManager();
        this.menuManager = new MenuManager();
        this.menuActionRegistry = new MenuActionRegistry(this, menuManager);
        this.cfgData = cfgData;
        this.listMenu = new ListMenu(this);
        this.deleteMenu = new DeleteMenu(this);
        this.settingsMenu = new  SettingsMenu(this);
        this.delayMenu = new DelayMenu(this);
        this.shareMenu = new ShareMenu(this);
        this.deleteAllMenu = new DeleteAllMenu(this);
        this.chooseHomeMenu = new ChooseHomeMenu(this);
        this.mainMenu = new MainMenu(this);
    }
}
