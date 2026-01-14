package traiwy.homePlugin.util;


import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class ConfigManager {
    private final JavaPlugin plugin;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }

    public ItemStack getMenuItem(Player player, String menuType, String key) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("menu." + menuType + "." + key);
        if (section == null) {
            player.sendMessage("§cЭлемент с ключом '" + key + "' не найден в '" + menuType + "'");
            return new ItemStack(Material.AIR);
        }

        try {
            String materialName = section.getString("item", "AIR").toUpperCase();
            Material material = Material.matchMaterial(materialName);
            if (material == null) {
                player.sendMessage("§cНеверный материал: " + materialName);
                return new ItemStack(Material.AIR);
            }

            String name = section.getString("name", "Без названия");
            List<String> lore = section.getStringList("lore");

            return createItem(material, name, lore);
        } catch (Exception e) {
            player.sendMessage("§cОшибка при создании пункта меню: " + key);
            e.printStackTrace();
            return new ItemStack(Material.AIR);
        }
    }

    private ItemStack createItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        var meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            if (lore != null && !lore.isEmpty()) {
                meta.setLore(lore);
            }
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    public void forceReplaceConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (configFile.exists()) {
            configFile.delete();
        }
        plugin.saveDefaultConfig();
    }
}