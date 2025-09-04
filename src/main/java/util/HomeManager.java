package util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
    public HomeManager(JavaPlugin plugin, File file, FileConfiguration config){
        this.plugin = plugin;
        this.file = file;
        this.config = config;
    }

    public HomeManager(JavaPlugin plugin){
        this.plugin = plugin;

        file = new File(plugin.getDataFolder(), "home.yml");
        if(!file.exists()){
            try {
               file.getParentFile().mkdirs();
               file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void SetHome(Player player, String nameHome, Location loc){
        String path = "homes." + player.getName() + "." + nameHome.toLowerCase();

        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        save();
    }

    public Location getHome(Player player, String nameHome){
        String path = "homes." + player.getName() + "." + nameHome.toLowerCase();
        if(!config.contains(path)) return null;

        String world = config.getString(path + ".world");
        double x = config.getDouble(path+ ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");

        return  new Location(Bukkit.getWorld(world),x,y,z);
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
    public boolean deleteAllHome(Player player) {
        String path = "homes." + player.getName();
        System.out.println("Проверяем путь: " + path); // Отладка

        if (!plugin.getConfig().isConfigurationSection(path)) {
            System.out.println("Секция " + path + " не найдена."); // Отладка
            return false;
        }

        System.out.println("Удаляем секцию: " + path); // Отладка
        plugin.getConfig().set(path, null);
        plugin.saveConfig();
        System.out.println("Сохранение выполнено."); // Отладка

        // Проверка, удалено ли
        if (!plugin.getConfig().isConfigurationSection(path)) {
            System.out.println("Секция " + path + " успешно удалена."); // Отладка
        } else {
            System.out.println("Секция " + path + " НЕ удалена."); // Отладка
        }

        return true;
    }
    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
}
