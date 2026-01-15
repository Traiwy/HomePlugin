package traiwy.homePlugin;

import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import traiwy.homePlugin.command.HomeCommand;
import traiwy.homePlugin.config.Config;
import traiwy.homePlugin.gui.MenuClickListener;
import traiwy.homePlugin.gui.menu.MainMenu;
import traiwy.homePlugin.gui.menu.ListMenu;
import traiwy.homePlugin.gui.menu.SettingsMenu;

public final class HomePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Config config = new Config(this);
        SettingsMenu settingsMenu = new SettingsMenu();
        ListMenu listMenu = new ListMenu(config.getConfigData(), settingsMenu);
        //HomeManager homeManager = new HomeManager();
        MainMenu mainMenu = new MainMenu(config.getConfigData(), listMenu, settingsMenu);
        PluginCommand command = getCommand("home");
        if(command != null) {
            command.setExecutor(new HomeCommand(mainMenu));
        } else {
            throw new RuntimeException("No command found!");
        }

        getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        //getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
    }

    @Override
    public void onDisable() {

    }
}
