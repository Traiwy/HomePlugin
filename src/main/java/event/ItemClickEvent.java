package event;

import invHolderMainMenu.ListHomeMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.HomeManager;

import static org.bukkit.Material.*;

public class ItemClickEvent implements Listener {
    private final HomeManager homeManager;
    private final ListHomeMenu listHomeMenu;
    public ItemClickEvent (HomeManager homeManager, ListHomeMenu listHomeMenu){
        this.homeManager = homeManager;
        this.listHomeMenu = listHomeMenu;
    }
    @EventHandler
    public  void ItemClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked(); //Игрок
        Inventory inventory = event.getClickedInventory(); //Инвентарь
        ItemStack item = event.getCurrentItem();

        if(item != null){
            switch (item.getType()){
                case ELYTRA -> player.performCommand("sethome");
                case APPLE -> listHomeMenu.ListHomeGUI(player);
                case DIAMOND ->  player.sendMessage("Нужно сделать отдельное меню");
                default -> {
                    break;
                }
            }
        }
        player.closeInventory();

    }
}
