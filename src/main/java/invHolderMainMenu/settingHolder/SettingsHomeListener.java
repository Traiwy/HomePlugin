package invHolderMainMenu.settingHolder;

import invHolderMainMenu.delayHolder.DelayMenuBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SettingsHomeListener implements Listener {
    private static final Map<UUID, Boolean > tripwireHookState = new HashMap<>();
    @EventHandler
    public void onSettingsHome(InventoryClickEvent e){
        var inv = e.getClickedInventory();
        var player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if(inv != null && inv.getHolder() instanceof  SettingsHomeHolder){
           if (item != null) {
                 e.setCancelled(true);
                 switch (item.getType()) {
                     case CLOCK:
                         DelayMenuBuilder.DelayGUI(player);
                         break;
                     case TRIPWIRE_HOOK:
                         toggleTripwiteHook(player,inv, e.getSlot());
                         break;
                     case PLAYER_HEAD:
                         break;
                     case NETHER_STAR:
                         break;
                     case REDSTONE_BLOCK:
                         break;
                     case REPEATER:
                         break;
                 }
             }
        }
    }
   private void toggleTripwiteHook(Player player, Inventory inventory, int slot) {
       UUID playerID = player.getUniqueId();
       boolean isEnchanted = tripwireHookState.getOrDefault(playerID, false);

       ItemStack tripwireHook = new ItemStack(Material.TRIPWIRE_HOOK);
       ItemMeta meta = tripwireHook.getItemMeta();

       if (meta != null) {
           meta.setDisplayName("");
           if (!isEnchanted) {
               meta.addEnchant(Enchantment.LUCK, 1, true);
               meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
           }
           tripwireHook.setItemMeta(meta);
       }
       inventory.setItem(slot, tripwireHook);
       tripwireHookState.put(playerID, !isEnchanted);
   }
   public static ItemStack getTripwireHookState(Player player){
        boolean isEnchanted = tripwireHookState.getOrDefault(player.getUniqueId(), false );
        ItemStack tripwireHook = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = tripwireHook.getItemMeta();
        if(meta != null){
            meta.setDisplayName("");
            if(isEnchanted){
                meta.addEnchant(Enchantment.LUCK,1,true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            }
            tripwireHook.setItemMeta(meta);
        }
        return tripwireHook;
    }
}
