package traiwy.homePlugin;

import command.ListHomeCommand;
import command.SethomeCommand;
import org.bukkit.plugin.java.JavaPlugin;
import util.SaveLocationPlayer;

public final class HomePlugin extends JavaPlugin {

    public SaveLocationPlayer saveLocationPlayer;
    @Override
    public void onEnable() {
        this.saveLocationPlayer = new SaveLocationPlayer(this);
        getCommand("sethome").setExecutor(new SethomeCommand(this, saveLocationPlayer));
        getCommand("listhome").setExecutor(new ListHomeCommand(this, saveLocationPlayer));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SaveLocationPlayer getSaveLocationPlayer() {
        return saveLocationPlayer;
    }
}
