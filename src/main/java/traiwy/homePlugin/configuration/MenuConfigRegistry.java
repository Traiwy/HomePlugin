package traiwy.homePlugin.configuration;

import traiwy.homePlugin.configuration.dto.ItemConfig;
import traiwy.homePlugin.configuration.dto.MenuConfig;

import java.util.Map;

public final class MenuConfigRegistry {

    private final Map<String, MenuConfig> menus;
    private final Map<String, ItemConfig> items;

    public MenuConfigRegistry(
            Map<String, MenuConfig> menus,
            Map<String, ItemConfig> items
    ) {
        this.menus = menus;
        this.items = items;
    }

    public MenuConfig menu(String id) {
        return menus.get(id);
    }

    public ItemConfig item(String id) {
        return items.get(id);
    }
}
