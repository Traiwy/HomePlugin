package traiwy.homePlugin;

import command.*;
import event.*;
import invHolderMainMenu.deleteHolder.DeleteHomeMenu;
import invHolderMainMenu.homeHolder.MainMenuHome;
import invHolderMainMenu.listHomeHolder.ListHomeMenu;
import org.bukkit.plugin.java.JavaPlugin;
import util.DeleteMapManager;
import util.HomeManager;

public final class HomePlugin extends JavaPlugin {

    public HomeManager homeManager;
    public ListHomeMenu listHomeMenu;
    public DeleteHomeMenu deleteHomeMenu;
    public MainMenuHome mainMenuHome;
    public DeleteMapManager deleteMapManager;
    @Override
    public void onEnable() {
        this.homeManager = new HomeManager(this);
        this.listHomeMenu = new ListHomeMenu(homeManager);
        this.deleteHomeMenu = new DeleteHomeMenu(homeManager);
        this.mainMenuHome = new MainMenuHome(this);
        this.deleteMapManager = new DeleteMapManager();
        getCommand("sethome").setExecutor(new SethomeCommand(this, homeManager));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("menuhome").setExecutor(new MenuHomeCommand(mainMenuHome));
        getCommand("delhome").setExecutor(new DeleteHomeCommand(homeManager));
        getServer().getPluginManager().registerEvents(new CanselClickInventory(), this);
        getServer().getPluginManager().registerEvents(new ItemClickEvent(homeManager, listHomeMenu, deleteHomeMenu,mainMenuHome), this);
        getServer().getPluginManager().registerEvents(new onPlayerChat(this, homeManager),this);
        getServer().getPluginManager().registerEvents(new ClickDeleteHomeEvent(deleteMapManager),this);
        getServer().getPluginManager().registerEvents(new ClickTeleportHome(homeManager), this);
        getServer().getPluginManager().registerEvents(new CloseInventoryEvent(deleteMapManager), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public HomeManager getHomeManager() {
        return homeManager;
    }
}
