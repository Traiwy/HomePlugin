package traiwy.homePlugin.configuration.dto;

import java.util.Map;

public record MenuConfig(
        String title,
        int size,
        Map<Integer, String> layout
) {}
