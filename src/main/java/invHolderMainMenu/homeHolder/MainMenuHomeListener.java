package invHolderMainMenu.homeHolder;


import invHolderMainMenu.listHomeHolder.ListHomeMenuBuilder;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MainMenuHomeListener implements Listener {
    private final ListHomeMenuBuilder listHomeMenuBuilder;
    private final SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    public MainMenuHomeListener(ListHomeMenuBuilder listHomeMenuBuilder, SettingsHomeMenuBuilder settingsHomeMenuBuilder){
        this.listHomeMenuBuilder = listHomeMenuBuilder;
        this.settingsHomeMenuBuilder = settingsHomeMenuBuilder;
    }
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        var player = (Player)e.getWhoClicked();
        var inv = e.getClickedInventory();
        var item = e.getCurrentItem();

        if(inv == null ||!(inv.getHolder() instanceof HomeHolder) || item == null) return;
        if(inv.getHolder() instanceof HomeHolder && item != null){
            switch (item.getType()){
                 case ELYTRA: player.performCommand("sethome");
                inv.close();
                break;
                case APPLE:
                    listHomeMenuBuilder.ListHomeGUI(player);
                    break;
                case DIAMOND:
                    settingsHomeMenuBuilder.SettingsGUI(player);
                    break;
                default:
                    break;
            }
        }
    }
}
