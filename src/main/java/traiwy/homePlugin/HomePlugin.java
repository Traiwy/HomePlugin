package traiwy.homePlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.home.CacheHome;
import traiwy.homePlugin.command.HomeCommand;
import traiwy.homePlugin.config.Config;
import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.db.MySqlRepository;
import traiwy.homePlugin.gui.MenuClickListener;
import traiwy.homePlugin.gui.menu.MainMenu;
import traiwy.homePlugin.gui.menu.ListMenu;
import traiwy.homePlugin.gui.menu.SettingsMenu;
import traiwy.homePlugin.listener.PlayerChatListener;
import traiwy.homePlugin.listener.PlayerCacheListener;

public final class HomePlugin extends JavaPlugin {
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        CacheHome cache = new CacheHome();
        Config config = new Config(this);
        databaseManager =  new DatabaseManager(config.getMySqlData());
        MySqlRepository mySqlRepository = new MySqlRepository(databaseManager);
        SettingsMenu settingsMenu = new SettingsMenu();
        ListMenu listMenu = new ListMenu(config.getConfigData(), settingsMenu, cache);
        PlayerChatListener playerChatListener = new PlayerChatListener(this, cache);
        MainMenu mainMenu = new MainMenu(config.getConfigData(), listMenu, settingsMenu);
        PluginCommand command = getCommand("home");
        if(command != null) {
            command.setExecutor(new HomeCommand(mainMenu, playerChatListener));
        } else {
            throw new RuntimeException("No command found!");
        }

        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        getServer().getPluginManager().registerEvents(playerChatListener, this);
        getServer().getPluginManager().registerEvents(new PlayerCacheListener(cache, mySqlRepository), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveListener(mySqlRepository, cache), this);
    }

    @Override
    public void onDisable() {
        databaseManager.shutdown();
    }
}
