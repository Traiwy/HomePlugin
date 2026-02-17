package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IconConfiguration {
    String type = "type";
    int amount = 0;
    int data = 0;
    String action = "action";
    String name = "name";
    List<String> lore = new ArrayList<>();


    public ItemStack build() {

        if (type == null) {
            throw new IllegalStateException("Material type is null");
        }

        String normalized = type.toUpperCase();

        if (normalized.contains(":")) {
            normalized = normalized.split(":")[1];
        }

        Material material = Material.matchMaterial(normalized);

        if (material == null) {
            throw new IllegalArgumentException("Invalid material: " + type);
        }

        final ItemStack item = new ItemStack(material, amount);

        final ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            if (name != null) meta.setDisplayName(name);
            if (lore != null) meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }
}
