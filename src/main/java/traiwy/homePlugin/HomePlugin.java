package traiwy.homePlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.CacheHome;
import traiwy.homePlugin.command.HomeCommand;
import traiwy.homePlugin.configuration.Config;
import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.db.home.MySqlHomeRepository;
import traiwy.homePlugin.db.member.MySqlMemberRepository;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.listener.PlayerCacheListener;
import traiwy.homePlugin.listener.PlayerChatListener;
import traiwy.homePlugin.manager.RequestManager;


public final class HomePlugin extends JavaPlugin {
    private DatabaseManager databaseManager;
    private CacheHome cache;

    @Override
    public void onEnable() {
        cache = new CacheHome();
        final Config config = new Config(this);
        final MenuService menuService = new MenuService(cache, config.getConfigData());
        databaseManager =  new DatabaseManager(config.getMySqlData());
        PlayerChatListener playerChatListener = new PlayerChatListener(this, cache);
        final MySqlHomeRepository mySqlHomeRepository = new MySqlHomeRepository(databaseManager);
        final MySqlMemberRepository mySqlMemberRepository = new MySqlMemberRepository(databaseManager);
        final RequestManager requestManager = new RequestManager();



        PluginCommand command = getCommand("home");
        if(command != null) {
            command.setExecutor(new HomeCommand(menuService, menuService.getMenuManager(), playerChatListener, requestManager));
            command.setTabCompleter(new HomeCommand(menuService, menuService.getMenuManager(), playerChatListener, requestManager));
        } else {
            throw new RuntimeException("No command found!");
        }
        getServer().getPluginManager().registerEvents(playerChatListener, this);
        getServer().getPluginManager().registerEvents(new PlayerCacheListener(cache, mySqlHomeRepository), this);
        getServer().getPluginManager().registerEvents(menuService.getMenuManager(), this);
    }

    @Override
    public void onDisable() {
        if (databaseManager != null) {
            databaseManager.shutdown();
        }
        cache.clear();
    }


}
