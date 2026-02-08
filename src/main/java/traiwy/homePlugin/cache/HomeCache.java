package traiwy.homePlugin.cache;

import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.home.Home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeCache {
    private final Map<String, List<Home>> homes = new HashMap<>();

    public void add(@NotNull String playerName, @NotNull Home home) {
        homes.computeIfAbsent(playerName, k -> new ArrayList<>()).add(home);
    }

    public List<Home> getAllHome(@NotNull String playerName) {
        List<Home> playerHomes = homes.getOrDefault(playerName, new ArrayList<>());

        for (Home home : playerHomes) {
            System.out.println(home.homeName());
        }

        return homes.getOrDefault(playerName, new ArrayList<>());
    }

    public void removeAllHome(@NotNull String playerName) {
        homes.remove(playerName);
    }

    public void remove(@NotNull String playerName, @NotNull Home home) {
        final List<Home> playerHomes = homes.get(playerName);
        if(playerHomes != null) {
            playerHomes.remove(home);
        }

    }

    public void clear() {
        homes.clear();
    }

}
