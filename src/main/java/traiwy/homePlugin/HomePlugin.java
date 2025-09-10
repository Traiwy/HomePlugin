package traiwy.homePlugin;

import command.*;
import event.*;
import invHolderMainMenu.delayHolder.DelayMenuBuilder;
import invHolderMainMenu.deleteHolder.DeleteHomeListener;
import invHolderMainMenu.deleteHolder.DeleteHomeMenuBuilder;
import invHolderMainMenu.homeHolder.MainMenuHomeBuilder;
import invHolderMainMenu.homeHolder.MainMenuHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeListener;
import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import invHolderMainMenu.settingHolder.SettingsHomeListener;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import invHolderMainMenu.shareHolder.ShareHomeMenuBuilder;
import invHolderMainMenu.shareHolder.ShareHomeMenuListener;
import org.bukkit.plugin.java.JavaPlugin;
import util.*;

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
        getCommand("home").setExecutor(new HomeCommand(this, homeManager, mainMenuHomeBuilder, settingsHomeMenuBuilder, listHomeMenuBuilder, shareHomeMenuBuilder));
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
