package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import traiwy.homePlugin.gui.BuiltMenu;

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

    public BuiltMenu build() {
        int rows = layout.size();

        final Inventory inv = Bukkit.createInventory(null, rows * 9, title);
        Map<Integer, IconConfiguration> slotIcons = new HashMap<>();
        List<Integer> dynamicSlots = new ArrayList<>();

        int slot = 0;

        for (String row : layout) {
            List<String> keys = parseRow(row);

            for (String key : keys) {
                if (key.equals("_")) {
                    dynamicSlots.add(slot);
                    slot++;
                    continue;
                }

                IconConfiguration icon = icons.get(key);
                if (icon != null) {
                    inv.setItem(slot, icon.build());
                    slotIcons.put(slot, icon); // ← ВАЖНО
                }

                slot++;
            }
        }

        return new BuiltMenu(inv, slotIcons, dynamicSlots);
    }

    private List<String> parseRow(String row) {
        List<String> result = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        boolean inQuotes = false;

        for (char c : row.toCharArray()) {
            if (c == '\'') {
                if (inQuotes) {
                    result.add(buffer.toString());
                    buffer.setLength(0);
                    inQuotes = false;
                } else {
                    inQuotes = true;
                }
                continue;
            }

            if (inQuotes) {
                buffer.append(c);
            } else {
                result.add(String.valueOf(c));
            }
        }

        return result;
    }
}