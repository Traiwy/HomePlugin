package traiwy.homePlugin;

import command.*;
import event.*;
import invHolderMainMenu.delayHolder.DelayMenu;
import invHolderMainMenu.deleteHolder.DeleteHomeListener;
import invHolderMainMenu.deleteHolder.DeleteHomeMenu;
import invHolderMainMenu.favoritesHolder.FavoritesHomeMenuBulder;
import invHolderMainMenu.homeHolder.MainMenuHome;
import invHolderMainMenu.homeHolder.MainMenuHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeMenu;
import invHolderMainMenu.settingHolder.SettingsHomeMenu;
import org.bukkit.plugin.java.JavaPlugin;
import util.ConfigManager;
import util.DeleteMapManager;
import util.HomeManager;

public final class HomePlugin extends JavaPlugin {

    public HomeManager homeManager;
    public ListHomeMenu listHomeMenu;
    public DeleteHomeMenu deleteHomeMenu;
    public MainMenuHome mainMenuHome;
    public DeleteMapManager deleteMapManager;
    public SettingsHomeMenu settingsHomeMenu;
    public DelayMenu delayMenu;
    public ConfigManager configManager;
    public FavoritesHomeMenuBulder favoritesHomeMenuBulder;
    @Override
    public void onEnable() {

        this.configManager = new ConfigManager(this);
         configManager.forceReplaceConfig();

         this.favoritesHomeMenuBulder = new FavoritesHomeMenuBulder(homeManager);
        this.homeManager = new HomeManager(this);
        this.listHomeMenu = new ListHomeMenu(homeManager,configManager);
        this.deleteHomeMenu = new DeleteHomeMenu(homeManager, this,configManager);
        this.mainMenuHome = new MainMenuHome(this,configManager);
        this.deleteMapManager = new DeleteMapManager();
        this.settingsHomeMenu = new SettingsHomeMenu(configManager);
        this.delayMenu = new DelayMenu();
        getCommand("sethome").setExecutor(new SethomeCommand(this, homeManager));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("menuhome").setExecutor(new MenuHomeCommand(mainMenuHome));
        getCommand("delhome").setExecutor(new DeleteHomeCommand(homeManager));
        getServer().getPluginManager().registerEvents(new CanselClickInventory(), this);
       getServer().getPluginManager().registerEvents(new ListHomeListener(deleteHomeMenu, favoritesHomeMenuBulder, homeManager,mainMenuHome), this);
       getServer().getPluginManager().registerEvents(new MainMenuHomeListener(listHomeMenu,settingsHomeMenu), this);
        getServer().getPluginManager().registerEvents(new onPlayerChat(this, homeManager),this);
        getServer().getPluginManager().registerEvents(new DeleteHomeListener(deleteMapManager),this);
        getServer().getPluginManager().registerEvents(new CloseInventoryEvent(deleteMapManager), this);
        getServer().getPluginManager().registerEvents(new ClickItemSettingsEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public HomeManager getHomeManager() {
        return homeManager;
    }
}
