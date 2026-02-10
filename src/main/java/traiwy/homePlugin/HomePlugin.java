package traiwy.homePlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.cache.MemberCache;
import traiwy.homePlugin.command.HomeCommand;
import traiwy.homePlugin.configuration.Config;
import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.db.home.MySqlHomeRepository;
import traiwy.homePlugin.db.member.MemberRepository;
import traiwy.homePlugin.db.member.MySqlMemberRepository;
import traiwy.homePlugin.facade.HomeFacade;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.listener.PlayerCacheListener;
import traiwy.homePlugin.listener.PlayerChatListener;
import traiwy.homePlugin.service.RepositoryService;


public final class HomePlugin extends JavaPlugin {
    private DatabaseManager databaseManager;
    private HomeCache cache;
    private MemberCache memberCache;

    @Override
    public void onEnable() {
        cache = new HomeCache();
        memberCache = new MemberCache();
        final Config config = new Config(this);
        databaseManager =  new DatabaseManager(config.getMySqlData());
        final MySqlHomeRepository mySqlHomeRepository = new MySqlHomeRepository(databaseManager);
        final MySqlMemberRepository mySqlMemberRepository = new MySqlMemberRepository(databaseManager);
        final RepositoryService repositoryService = new RepositoryService(mySqlHomeRepository, mySqlMemberRepository);
        final HomeFacade homeFacade = new HomeFacade(repositoryService, cache, memberCache);
        final MenuService menuService = new MenuService(cache, config.getConfigData(), homeFacade);
        PlayerChatListener playerChatListener = new PlayerChatListener(this, homeFacade);




        PluginCommand command = getCommand("home");
        if(command != null) {
            command.setExecutor(new HomeCommand(menuService, menuService.getMenuManager(), playerChatListener, menuService.getRequestManager(), menuService.getInviteContextManager()));
            command.setTabCompleter(new HomeCommand(menuService, menuService.getMenuManager(), playerChatListener, menuService.getRequestManager(), menuService.getInviteContextManager()));
        } else {
            throw new RuntimeException("No command found!");
        }
        getServer().getPluginManager().registerEvents(playerChatListener, this);
        getServer().getPluginManager().registerEvents(new PlayerCacheListener(homeFacade, menuService.getMenuManager()), this);
        getServer().getPluginManager().registerEvents(menuService.getMenuManager(), this);
    }

    @Override
    public void onDisable() {
        if (databaseManager != null) {
            databaseManager.shutdown();
        }
        cache.clear();
        memberCache.clear();
    }


}
