package traiwy.homePlugin.listener;

import traiwy.homePlugin.gui.deleteHolder.DeleteMenuHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import traiwy.homePlugin.util.DeleteMapManager;

import java.util.UUID;

public class CloseInventoryListener implements Listener {
    private final DeleteMapManager deleteMapManager;
    public CloseInventoryListener(DeleteMapManager deleteMapManager){
        this.deleteMapManager = deleteMapManager;
    }
    @EventHandler
    public void CloseInventory(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        UUID target = player.getUniqueId();

        if(event.getInventory().getHolder() instanceof DeleteMenuHolder){
            deleteMapManager.removeAwaitingClickDeleteHome(player);
        }
    }
}
