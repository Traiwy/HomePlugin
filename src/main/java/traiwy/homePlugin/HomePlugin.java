package traiwy.homePlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.cache.HomeCache;
import traiwy.homePlugin.cache.MemberCache;
import traiwy.homePlugin.command.HomeCommand;
import traiwy.homePlugin.configuration.Configuration;
import traiwy.homePlugin.db.DatabaseManager;
import traiwy.homePlugin.db.home.MySqlHomeRepository;
import traiwy.homePlugin.db.member.MySqlMemberRepository;
import traiwy.homePlugin.error.ErrorService;
import traiwy.homePlugin.facade.HomeFacade;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.listener.PlayerCacheListener;
import traiwy.homePlugin.listener.PlayerChatListener;
import traiwy.homePlugin.service.RepositoryService;


public final class HomePlugin extends JavaPlugin {
    private DatabaseManager databaseManager;
    private HomeCache cache;
    private MemberCache memberCache;

    private Configuration configuration;
    private HomeFacade homeFacade;
    private MenuService menuService;
    private PlayerChatListener playerChatListener;
    private ErrorService errorService;

    @Override
    public void onEnable() {
        initializeCore();
        initializeServices();
        registerCommand();
        registerListeners();
    }

    @Override
    public void onDisable() {
        shutdownDatabase();
        clearCaches();
    }

    private void initializeCore() {
        cache = new HomeCache();
        memberCache = new MemberCache();
        configuration = new Configuration(this);
        errorService = new ErrorService(configuration.getConfiguration().error());
        databaseManager = new DatabaseManager(configuration.getConfiguration().sql());
    }

    private void initializeServices() {
        MySqlHomeRepository homeRepository = new MySqlHomeRepository(databaseManager);
        MySqlMemberRepository memberRepository = new MySqlMemberRepository(databaseManager);

        RepositoryService repositoryService =
                new RepositoryService(homeRepository, memberRepository);

        homeFacade = new HomeFacade(repositoryService, cache, memberCache, errorService);
        menuService = new MenuService(cache, configuration, homeFacade);
        playerChatListener = new PlayerChatListener(this, homeFacade);
    }

    private void registerCommand() {
        PluginCommand command = getCommand("home");

        if (command == null) {
            throw new IllegalStateException("Command 'home' not found in plugin.yml");
        }

        HomeCommand homeCommand = new HomeCommand(
                menuService,
                menuService.getMenuManager(),
                playerChatListener,
                menuService.getRequestManager(),
                menuService.getInviteContextManager(),
                errorService
        );

        command.setExecutor(homeCommand);
        command.setTabCompleter(homeCommand);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(playerChatListener, this);
        getServer().getPluginManager().registerEvents(
                new PlayerCacheListener(homeFacade, menuService.getMenuManager()), this);
        getServer().getPluginManager().registerEvents(
                menuService.getMenuManager(), this);
    }

    private void shutdownDatabase() {
        if (databaseManager != null) {
            databaseManager.shutdown();
        }
    }

    private void clearCaches() {
        if (cache != null) {
            cache.clear();
        }

        if (memberCache != null) {
            memberCache.clear();
        }
    }
}
