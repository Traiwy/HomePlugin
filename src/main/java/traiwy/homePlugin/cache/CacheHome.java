package traiwy.homePlugin.cache;

import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.LocationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheHome {
    private final Map<String, List<Home>> homes = new HashMap<>();

    public void add(@NotNull String playerName, @NotNull Home home) {
        homes.computeIfAbsent(playerName, k -> new ArrayList<>()).add(home);
    }

    public List<Home> getAllHome(@NotNull String playerName) {
        return homes.getOrDefault(playerName, new ArrayList<>());
    }

    public void remove(@NotNull String playerName) {
        homes.remove(playerName);
    }

    public void clear() {
        homes.clear();
    }

}
