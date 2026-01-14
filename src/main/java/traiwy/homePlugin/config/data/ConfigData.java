package traiwy.homePlugin.config.data;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ConfigData {
    private Map<String, Map<String, MenuItemConfig>> menu;

    public static ConfigData defaultConfig() {
        ConfigData data = new ConfigData();
        data.menu = new HashMap<>();
        return data;
    }
}
