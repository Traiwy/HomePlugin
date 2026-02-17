package traiwy.homePlugin.util;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class PlaceholderUtil {
    public String apply(String text, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            text = text.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return text;
    }
}
