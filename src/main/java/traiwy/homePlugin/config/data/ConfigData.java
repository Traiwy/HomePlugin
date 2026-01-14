package traiwy.homePlugin.config.data;

import java.util.List;
import java.util.Map;

public record ConfigData(
        Map<String, MenuConfig> menu
) {
    public static ConfigData defaultConfig() {
        return new ConfigData(Map.of(
                "mainmenu", new MenuConfig(Map.of(
                        "elytra", new MenuItemConfig("ELYTRA", "§aCreate at home", List.of()),
                        "apple", new MenuItemConfig("APPLE", "§bList home", List.of()),
                        "diamond", new MenuItemConfig("DIAMOND", "§4Settings", List.of())
                )),
                "listmenu", new MenuConfig(Map.of(
                        "arrow", new MenuItemConfig("ARROW", "§bBack", List.of("§7Click to return")),
                        "limedye", new MenuItemConfig("LIME_DYE", "§aEnable deletion mode", List.of(
                                "§c§l Press to enter the mode",
                                "§c§l delete. In this mode, you can",
                                "§c§l delete your houses by simply clicking on them."
                        )),
                        "nether_star", new MenuItemConfig("NETHER_STAR", "Favorite Homes", List.of())
                )),
                "settingsmenu", new MenuConfig(Map.of(
                        "clock", new MenuItemConfig("CLOCK", "§bDelay", List.of()),
                        "tripwirehook", new MenuItemConfig("TRIPWIRE_HOOK", "§bAccess", List.of()),
                        "player_head", new MenuItemConfig("PLAYER_HEAD", "§bShare with friends", List.of()),
                        "nether_star", new MenuItemConfig("NETHER_STAR", "§bFavorite homes", List.of()),
                        "redstone_block", new MenuItemConfig("REDSTONE_BLOCK", "§cDelete all home", List.of()),
                        "repeater", new MenuItemConfig("REPEATER", "§bSettings accesss", List.of()),
                        "arrow", new MenuItemConfig("ARROW", "§bBack", List.of("§7Click to return"))
                )),
                "deletemenu", new MenuConfig(Map.of(
                        "arrow", new MenuItemConfig("ARROW", "§bBack", List.of("§7Click to return")),
                        "reddye", new MenuItemConfig("RED_DYE", "§cTurn off the deletion mode", List.of(
                                "§c§lClick to exit the mode",
                                "§c§ldeletion. In this mode, you can",
                                "§c§lteleport to your homes",
                                "§c§lby simply clicking on them"
                        ))
                ))
        ));
    }
}
