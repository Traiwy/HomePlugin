package traiwy.homePlugin.configuration.dto;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

public record ConfigData(
        Map<String, MenuConfig> menus,
        Map<String, ItemConfig> items
) {
}
