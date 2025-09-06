package util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeManager {
    private final JavaPlugin plugin;
    private final File file;
    private final FileConfiguration config;

    public HomeManager(JavaPlugin plugin, File file, FileConfiguration config) {
        this.plugin = plugin;
        this.file = file;
        this.config = config;
    }

    public HomeManager(JavaPlugin plugin) {
        this.plugin = plugin;

        file = new File(plugin.getDataFolder(), "home.yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setHome(Player owner, String nameHome, Location loc, String realOwner) {
        String path = "homes." + owner.getName() + "." + nameHome.toLowerCase();


        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".owner", realOwner);
        save();
    }


    public Location getHome(Player player, String nameHome) {
        String path = "homes." + player.getName() + "." + nameHome.toLowerCase();
        if (!config.contains(path)) return null;

        String world = config.getString(path + ".world");
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");

        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public Set<String> getHomeNames(Player player) {
        String path = "homes." + player.getName();
        if (!config.contains(path)) return new HashSet<>();
        return config.getConfigurationSection(path).getKeys(false);
    }

    public void deleteHome(Player player, String homeName) {
        String path = "homes." + player.getName() + "." + homeName.toLowerCase();
        config.set(path, null);
        save();
    }

    public boolean deleteAllHomes(Player player) {
        String playerName = player.getName();
        String basePath = "homes." + playerName;
        ConfigurationSection playerSection = config.getConfigurationSection(basePath);
        if (playerSection == null) {
            return false;
        }
        Set<String> homeNames = playerSection.getKeys(false);
        if (homeNames.isEmpty()) {
            return false;
        }
        try {
            config.set(basePath, null);
            plugin.saveConfig();
            plugin.reloadConfig();
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка при удалении домов: " + e.getMessage());
            return false;
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
