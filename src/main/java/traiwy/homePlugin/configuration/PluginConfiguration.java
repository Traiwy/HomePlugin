package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PluginConfiguration {
    final SqlConfiguration sql = new SqlConfiguration();
    final Map<String, MenuConfiguration> menus = new HashMap<>(Map.of("main_menu", new MenuConfiguration()));
    final ErrorMessageConfiguration error = new ErrorMessageConfiguration();
}
