package traiwy.homePlugin.configuration.dto;

import java.util.List;

public record ItemConfig(
        String material,
        String name,
        List<String> lore
) {}
