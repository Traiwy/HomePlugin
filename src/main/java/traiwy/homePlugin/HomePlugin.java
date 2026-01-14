package traiwy.homePlugin;

import traiwy.homePlugin.command.*;
import traiwy.homePlugin.listener.*;
import traiwy.homePlugin.gui.delayHolder.DelayMenuBuilder;
import traiwy.homePlugin.gui.deleteHolder.DeleteHomeListener;
import traiwy.homePlugin.gui.deleteHolder.DeleteHomeMenuBuilder;
import traiwy.homePlugin.gui.homeHolder.MainMenuHomeBuilder;
import traiwy.homePlugin.gui.homeHolder.MainMenuHomeListener;
import traiwy.homePlugin.gui.listHomeHolder.ListHomeListener;
import traiwy.homePlugin.gui.listHomeHolder.ListHomeMenuBuilder;
import traiwy.homePlugin.gui.settingHolder.SettingsHomeListener;
import traiwy.homePlugin.gui.settingHolder.SettingsHomeMenuBuilder;
import traiwy.homePlugin.gui.shareHolder.ShareHomeMenuBuilder;
import traiwy.homePlugin.gui.shareHolder.ShareHomeMenuListener;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.util.*;

public final class HomePlugin extends JavaPlugin {


    public ConfigManager configManager;
    public HomeManager homeManager;
    public ListHomeMenuBuilder listHomeMenuBuilder;
    public DeleteHomeMenuBuilder deleteHomeMenuBuilder;
    public MainMenuHomeBuilder mainMenuHomeBuilder;
    public DeleteMapManager deleteMapManager;
    public SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    public DelayMenuBuilder delayMenu;
    public ConfirmationManagerDeleteHome confirmationManagerDeleteHome;
    public ShareHomeMenuBuilder shareHomeMenuBuilder;
    public ConfirmationManagerShareHome confirmationManagerShareHome;
    public ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer;

    @Override
    public void onEnable() {

        this.configManager = new ConfigManager(this);
        configManager.forceReplaceConfig();
        this.homeManager = new HomeManager(this);
        this.listHomeMenuBuilder = new ListHomeMenuBuilder(homeManager, configManager);
        this.deleteHomeMenuBuilder = new DeleteHomeMenuBuilder(homeManager, this, configManager);
        this.mainMenuHomeBuilder = new MainMenuHomeBuilder(this, configManager);
        this.deleteMapManager = new DeleteMapManager();
        this.settingsHomeMenuBuilder = new SettingsHomeMenuBuilder(configManager);
        this.delayMenu = new DelayMenuBuilder();
        this.confirmationManagerDeleteHome = new ConfirmationManagerDeleteHome();
        this.shareHomeMenuBuilder = new ShareHomeMenuBuilder(homeManager, configManager);
        this.confirmationManagerShareHome = new ConfirmationManagerShareHome();
        this.confirmationManagerShareMessagePlayer = new ConfirmationManagerShareMessagePlayer();
        //регистрация команд
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("homeaccept").setExecutor(new HomeAcceptCommand(homeManager, confirmationManagerShareMessagePlayer));
        getCommand("homecancel").setExecutor(new HomeCancelCommand(confirmationManagerShareMessagePlayer));
        //регистрация ивентов
        getServer().getPluginManager().registerEvents(new CanselClickInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new ListHomeListener(deleteHomeMenuBuilder, homeManager, mainMenuHomeBuilder), this);
        getServer().getPluginManager().registerEvents(new MainMenuHomeListener(listHomeMenuBuilder, settingsHomeMenuBuilder), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(this, homeManager), this);
        getServer().getPluginManager().registerEvents(new DeleteHomeListener(deleteMapManager, listHomeMenuBuilder, homeManager), this);
        getServer().getPluginManager().registerEvents(new CloseInventoryListener(deleteMapManager), this);
        getServer().getPluginManager().registerEvents(new SettingsHomeListener(mainMenuHomeBuilder, confirmationManagerDeleteHome, this, homeManager, shareHomeMenuBuilder), this);
        getServer().getPluginManager().registerEvents(new ShareHomeMenuListener(settingsHomeMenuBuilder, confirmationManagerShareHome, confirmationManagerShareMessagePlayer), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public HomeManager getHomeManager() {
        return homeManager;
    }
}
