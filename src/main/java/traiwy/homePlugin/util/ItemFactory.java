package traiwy.homePlugin.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import traiwy.homePlugin.config.data.MenuItemConfig;

@UtilityClass
public class ItemFactory {
    public ItemStack create(MenuItemConfig config) {
        final Material material = Material.valueOf(config.item());

        final ItemStack item = new ItemStack(material);
        final ItemMeta meta = item.getItemMeta();

        if(config.name() != null) meta.setDisplayName(config.name());
        if(config.lore() != null) meta.setLore(config.lore());
        item.setItemMeta(meta);
        return item;
    }
}
