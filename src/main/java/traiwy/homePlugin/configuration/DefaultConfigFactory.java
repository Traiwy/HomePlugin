package traiwy.homePlugin.configuration;

import traiwy.homePlugin.configuration.dto.ConfigData;
import traiwy.homePlugin.configuration.dto.ItemConfig;
import traiwy.homePlugin.configuration.dto.MenuConfig;

import java.util.List;
import java.util.Map;

public class DefaultConfigFactory {
    private DefaultConfigFactory() {}

    public static ConfigData create() {

        Map<String, ItemConfig> items = Map.of(
                "create_home", item("ELYTRA", "§aCreate at home"),
                "list_home", item("APPLE", "§bList home"),
                "settings", item("DIAMOND", "§4Settings"),
                "back", item("ARROW", "§bBack", "§7Click to return"),
                "delete_on", item(
                        "LIME_DYE",
                        "§aEnable deletion mode",
                        "§c§lPress to enter delete mode",
                        "§c§lClick homes to delete them"
                ),
                "delete_off", item(
                        "RED_DYE",
                        "§cTurn off deletion mode",
                        "§c§lClick to exit delete mode"
                )
        );

        Map<String, MenuConfig> menus = Map.of(
                "mainmenu", menu("§bMain menu", 27,
                        Map.of(11, "create_home", 13, "list_home", 15, "settings")
                ),
                "listmenu", menu("§bHomes", 27,
                        Map.of(11, "delete_on", 13, "list_home", 15, "back")
                ),
                "deletemenu", menu("§cDelete mode", 27,
                        Map.of(13, "delete_off", 26, "back")
                )
        );

        return new ConfigData(menus, items);
    }

    private static ItemConfig item(String material, String name, String... lore) {
        return new ItemConfig(material, name, List.of(lore));
    }

    private static MenuConfig menu(String title, int size, Map<Integer, String> layout) {
        return new MenuConfig(title, size, layout);
    }
}
