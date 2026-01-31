package traiwy.homePlugin.configuration.dto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

public record ConfigData(
        Map<String, MenuConfig> menus,
        Map<String, ItemConfig> items
) {
    public static ConfigData defaultConfig() {

        Map<String, ItemConfig> items = Map.of(
                "create_home", item("ELYTRA", "§aCreate at home"),
                "list_home", item("APPLE", "§bList home"),
                "settings", item("DIAMOND", "§4Settings"),

                "back", item("ARROW", "§bBack", "§7Click to return"),
                "delete_on", item(
                        "LIME_DYE",
                        "§aEnable deletion mode",
                        "§c§l Press to enter the mode",
                        "§c§l delete. In this mode, you can",
                        "§c§l delete your houses by simply clicking on them."
                ),
                "delete_off", item(
                        "RED_DYE",
                        "§cTurn off the deletion mode",
                        "§c§lClick to exit the mode",
                        "§c§ldeletion. In this mode, you can",
                        "§c§lteleport to your homes",
                        "§c§lby simply clicking on them"
                )
        );

        Map<String, MenuConfig> menus = Map.of(
                "mainmenu", menu(
                        "§bMain menu",
                        27,
                        Map.of(
                                11, "create_home",
                                13, "list_home",
                                15, "settings"
                        )
                ),

                "listmenu", menu(
                        "§bHomes",
                        27,
                        Map.of(
                                11, "delete_on",
                                13, "list_home",
                                15, "back"
                        )
                ),

                "deletemenu", menu(
                        "§cDelete mode",
                        27,
                        Map.of(
                                13, "delete_off",
                                26, "back"
                        )
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
