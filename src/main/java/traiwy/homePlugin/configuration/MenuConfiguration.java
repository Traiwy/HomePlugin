package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuConfiguration {
    String type = "type";
    String title = "title";
    List<String> layout = new ArrayList<>(List.of(
            "ABC",
            "DEF"
    ));
    Map<String, IconConfiguration> icons = new HashMap<>(Map.of(
            "A", new IconConfiguration()
    ));

    public Inventory build() {
        int rows = layout.size();

        final Inventory inv = Bukkit.createInventory(
                null,
                rows * 9,
                title
        );

        int slot = 0;

        for (String row : layout) {
            for (char key : row.toCharArray()) {

                if (key == '_') {
                    slot++;
                    continue;
                }

                IconConfiguration icon = icons.get(String.valueOf(key));
                if (icon != null) {
                    inv.setItem(slot, icon.build());
                }

                slot++;
            }
        }

        return inv;
    }
}