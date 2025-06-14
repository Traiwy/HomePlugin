package traiwy.homePlugin;

import command.HomeCommand;
import command.ListHomeCommand;
import command.MenuHomeCommand;
import command.SethomeCommand;
import org.bukkit.plugin.java.JavaPlugin;
import util.HomeManager;

public final class HomePlugin extends JavaPlugin {

    public HomeManager homeManager;
    @Override
    public void onEnable() {
        this.homeManager = new HomeManager(this);
        getCommand("sethome").setExecutor(new SethomeCommand(this, homeManager));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("menuhome").setExecutor(new MenuHomeCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public HomeManager getHomeManager() {
        return homeManager;
    }
}
