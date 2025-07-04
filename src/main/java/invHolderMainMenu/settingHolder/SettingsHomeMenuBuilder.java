package invHolderMainMenu.settingHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import util.ConfigManager;

public class SettingsHomeMenuBuilder {
    private final ConfigManager configManager;
    public SettingsHomeMenuBuilder(ConfigManager configManager){
        this.configManager = configManager;
    }
    public void SettingsGUI(Player player){
        Inventory inv = Bukkit.createInventory(new SettingsHomeHolder(), 27, "Настройки" );

        var clock = configManager.getMenuItem(player, "settingsmenu","clock");
        var player_head = configManager.getMenuItem(player, "settingsmenu","player_head");
        var redstone_block = configManager.getMenuItem(player, "settingsmenu","redstone_block");
        var arrow = configManager.getMenuItem(player, "settingsmenu","arrow");

        inv.setItem(10,clock);
        inv.setItem(12, SettingsHomeListener.getTripwireHookState(player));
        inv.setItem(14, player_head);
        inv.setItem(16, redstone_block);
        inv .setItem(18, arrow);


         ItemStack pinkPanel = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        int[] countPinkPanel = {0,1,2,3,4,5,6,7,8,9,19,20,21,22,23,24,25,26,17};
        for(int i = 0; i <countPinkPanel.length; i++){
            inv.setItem(countPinkPanel[i], pinkPanel);
        }

        player.openInventory(inv);



    }
}
