package event;

import invHolderMainMenu.deleteHolder.DeleteHomeMenu;
import invHolderMainMenu.listHomeHolder.ListHomeMenu;
import invHolderMainMenu.homeHolder.MainMenuHome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.HomeManager;

public class ItemClickEvent implements Listener {
    private final HomeManager homeManager;
    private final ListHomeMenu listHomeMenu;
    private final DeleteHomeMenu deleteHomeMenu;
    private final MainMenuHome mainMenuHome;
    public ItemClickEvent (HomeManager homeManager, ListHomeMenu listHomeMenu, DeleteHomeMenu deleteHomeMenu, MainMenuHome mainMenuHome){
        this.homeManager = homeManager;
        this.listHomeMenu = listHomeMenu;
        this.deleteHomeMenu = deleteHomeMenu;
        this.mainMenuHome = mainMenuHome;
    }
    @EventHandler
    public  void ItemClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked(); //Игрок
        Inventory inventory = event.getClickedInventory(); //Инвентарь
        ItemStack item = event.getCurrentItem();

        if(item != null){
            switch (item.getType()){
                case ELYTRA:
                    player.performCommand("sethome");
                    inventory.close();
                    break;
                case APPLE:
                    listHomeMenu.ListHomeGUI(player);
                    break;
                case DIAMOND:
                    player.sendMessage("Нужно сделать отдельное меню");
                    break;
                case ARROW:
                    mainMenuHome.HomeGUI(player);
                    break;
                case LIME_DYE:
                    deleteHomeMenu.DeleteHomeGUI(player);
                    break;
                case RED_DYE:
                    listHomeMenu.ListHomeGUI(player);
                    break;
                default:
                    break;

            }
        }

    }
}
