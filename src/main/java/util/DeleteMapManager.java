package util;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeleteMapManager {
    private final Map<UUID, Boolean> awaitingClickDeleteHome = new HashMap<>();

    public Map<UUID, Boolean> getAwaitingClickDeleteHome(){
        return awaitingClickDeleteHome;
    }
    public void addAwaitingClickDeleteHome(Player player){
        awaitingClickDeleteHome.put(player.getUniqueId(), true);
        player.sendMessage("Игрок был добавлен");
    }
    public void removeAwaitingClickDeleteHome(Player player){
        awaitingClickDeleteHome.put(player.getUniqueId(), false);
        player.sendMessage("Игрок был удален");
    }
    public boolean containsAwaitingClickDeleteHome(Player player){
        return awaitingClickDeleteHome.containsKey(player.getUniqueId());
    }
}
