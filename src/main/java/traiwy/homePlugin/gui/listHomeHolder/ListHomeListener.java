package traiwy.homePlugin.gui.listHomeHolder;

import traiwy.homePlugin.gui.deleteHolder.DeleteHomeMenuBuilder;
import traiwy.homePlugin.gui.homeHolder.MainMenuHomeBuilder;
import org.bukkit.event.Listener;


public class ListHomeListener implements Listener {
    private final DeleteHomeMenuBuilder deleteHomeMenuBuilder;
    private final MainMenuHomeBuilder mainMenuHomeBuilder;

    public ListHomeListener(DeleteHomeMenuBuilder deleteHomeMenuBuilder,MainMenuHomeBuilder mainMenuHomeBuilder){
        this.mainMenuHomeBuilder = mainMenuHomeBuilder;
        this.deleteHomeMenuBuilder = deleteHomeMenuBuilder;
    }
   //@EventHandler
   //public void onInventoryClickEvent(InventoryClickEvent e){
   //    var player = (Player)e.getWhoClicked();
   //    var inv = e.getClickedInventory();
   //    var item = e.getCurrentItem();

   //    if(inv == null ||!(inv.getHolder() instanceof ListHomeHolder) || item == null) return;
   //    if(inv != null && inv.getHolder() instanceof ListHomeHolder){

   //        switch (item.getType()){
   //            case LIME_DYE:
   //                deleteHomeMenuBuilder.DeleteHomeGUI(player);
   //                break;
   //            case ARROW:
   //                mainMenuHomeBuilder.HomeGUI(player);
   //                break;
   //            default:
   //                break;

   //        }
   //    }

   //}
   //@EventHandler
   //public void TeleportHome(InventoryClickEvent event){
   //    if (!(event.getWhoClicked() instanceof Player)) return;
   //    Player player = (Player) event.getWhoClicked();
   //    Inventory clickInv = event.getClickedInventory();
   //    ItemStack item = event.getCurrentItem();
   //    if(clickInv.getHolder() == null) return;
   //    if(clickInv.getHolder() instanceof ListHomeHolder){
   //        if(item != null && item.getType() == Material.PLAYER_HEAD){
   //            ItemMeta meta = item.getItemMeta();
   //            if (!meta.hasDisplayName()) {
   //                player.sendMessage("§cУ этого дома нет названия!");
   //                return;
   //            }
   //            String homeName = meta.getDisplayName();
   //            Location location = homeManager.getHome(player, homeName);
   //            if(location == null){
   //                player.sendMessage("Дом не найден");
   //            }
   //            player.teleport(location);
   //            player.sendMessage("Вы телепортированы в дом" + homeName);
   //            player.closeInventory();
   //        }
   //    }
   //}
}
