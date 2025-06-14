package traiwy.homePlugin;

import command.HomeCommand;
import command.ListHomeCommand;
import command.MenuHomeCommand;
import command.SethomeCommand;
import event.CanselClickInventory;
import event.ItemClickEvent;
import event.onPlayerChat;
import invHolderMainMenu.ListHomeMenu;
import org.bukkit.plugin.java.JavaPlugin;
import util.HomeManager;

public final class HomePlugin extends JavaPlugin {

    public HomeManager homeManager;
    public ListHomeMenu listHomeMenu;
    @Override
    public void onEnable() {
        this.homeManager = new HomeManager(this);
        this.listHomeMenu = new ListHomeMenu();
        getCommand("sethome").setExecutor(new SethomeCommand(this, homeManager));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("menuhome").setExecutor(new MenuHomeCommand());
        getServer().getPluginManager().registerEvents(new CanselClickInventory(), this);
        getServer().getPluginManager().registerEvents(new ItemClickEvent(homeManager, listHomeMenu), this);
        getServer().getPluginManager().registerEvents(new onPlayerChat(this, homeManager),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public HomeManager getHomeManager() {
        return homeManager;
    }
}
