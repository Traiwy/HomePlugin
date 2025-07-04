package traiwy.homePlugin;

import command.*;
import event.*;
import invHolderMainMenu.delayHolder.DelayMenuBuilder;
import invHolderMainMenu.deleteHolder.DeleteHomeListener;
import invHolderMainMenu.deleteHolder.DeleteHomeMenuBuilder;
import invHolderMainMenu.favoritesHolder.FavoritesHomeMenuBuilder;
import invHolderMainMenu.homeHolder.MainMenuHomeBuilder;
import invHolderMainMenu.homeHolder.MainMenuHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import invHolderMainMenu.settingHolder.SettingsHomeListener;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import util.ConfigManager;
import util.DeleteMapManager;
import util.HomeManager;

public final class HomePlugin extends JavaPlugin {

    public HomeManager homeManager;
    public ListHomeMenuBuilder listHomeMenuBuilder;
    public DeleteHomeMenuBuilder deleteHomeMenuBuilder;
    public MainMenuHomeBuilder mainMenuHomeBuilder;
    public DeleteMapManager deleteMapManager;
    public SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    public DelayMenuBuilder delayMenu;
    public ConfigManager configManager;
    public FavoritesHomeMenuBuilder favoritesHomeMenuBuilder;
    @Override
    public void onEnable() {

        this.configManager = new ConfigManager(this);
         configManager.forceReplaceConfig();
        this.homeManager = new HomeManager(this);
        this.listHomeMenuBuilder = new ListHomeMenuBuilder(homeManager,configManager);
        this.deleteHomeMenuBuilder = new DeleteHomeMenuBuilder(homeManager, this,configManager);
        this.mainMenuHomeBuilder = new MainMenuHomeBuilder(this,configManager);
        this.deleteMapManager = new DeleteMapManager();
        this.settingsHomeMenuBuilder = new SettingsHomeMenuBuilder(configManager);
         this.favoritesHomeMenuBuilder = new FavoritesHomeMenuBuilder(homeManager);
        this.delayMenu = new DelayMenuBuilder();
        //регистрация команд
        getCommand("sethome").setExecutor(new SethomeCommand(this, homeManager));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("menuhome").setExecutor(new MenuHomeCommand(mainMenuHomeBuilder));
        getCommand("delhome").setExecutor(new DeleteHomeCommand(homeManager));
        //регистрация ивентов
        getServer().getPluginManager().registerEvents(new CanselClickInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ListHomeListener(deleteHomeMenuBuilder, favoritesHomeMenuBuilder, homeManager, mainMenuHomeBuilder), this);
        getServer().getPluginManager().registerEvents(new MainMenuHomeListener(listHomeMenuBuilder, settingsHomeMenuBuilder), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(this, homeManager),this);
        getServer().getPluginManager().registerEvents(new DeleteHomeListener(deleteMapManager, listHomeMenuBuilder),this);
        getServer().getPluginManager().registerEvents(new CloseInventoryListener(deleteMapManager), this);
        getServer().getPluginManager().registerEvents(new SettingsHomeListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public HomeManager getHomeManager() {
        return homeManager;
    }
}
