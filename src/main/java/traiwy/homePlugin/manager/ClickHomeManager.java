package traiwy.homePlugin.manager;

import org.bukkit.entity.Player;
import traiwy.homePlugin.home.Home;

import java.util.HashMap;
import java.util.Map;

public class ClickHomeManager {
    private final Map<Player, Home> clickHome = new HashMap<>();

    public void addHome(Player player, Home home) {
        clickHome.put(player, home);
    }

    public void removeHome(Player player, Home home) {
        clickHome.remove(player);
    }

    public Home getHome(Player player) {
        return clickHome.get(player);
    }


}
