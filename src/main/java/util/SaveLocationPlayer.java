package util;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class SaveLocationPlayer {
    private final JavaPlugin plugin;
    public SaveLocationPlayer(JavaPlugin plugin){
        this.plugin = plugin;
    }
    Map<String, Location> playerLocation = new HashMap<>();
    public Map<String, Location> SaveLocation(Player player){
        Location location = player.getLocation();
        String name =  player.getName();
        playerLocation.put(name, location);
        return playerLocation;
    }
    public void ListLocationPlayer(Player player){
       player.sendMessage("Ваши точки домов: ");
        for(Map.Entry<String, Location> entry : playerLocation.entrySet()){
            String name = entry.getKey();
            Location location = entry.getValue();
            player.sendMessage(name + ": x: " + location.getBlockX() + " y: " + location.getBlockY()+" z: " +location.getBlockZ());
        }
    }

}
