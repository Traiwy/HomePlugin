package traiwy.homePlugin.cache.home;

import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.LocationData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheHome {
    private final Map<String, Home> homes = new HashMap<>();

    public void add(@NotNull String name, @NotNull Home home) {
        homes.put(name, home);
    }

    public Home get(@NotNull String name) {
        return homes.get(name);
    }


    public List<Home> getAllHome(@NotNull String name) {
        return new ArrayList<>(homes.values());
    }

    public void remove(@NotNull String name) {
        homes.remove(name);
    }

}
