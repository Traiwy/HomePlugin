package invHolderMainMenu.listHomeHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import util.ConfigManager;
import util.HomeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ListHomeMenuBuilder {
    private final HomeManager homeManager;
    private final ConfigManager configManager;
    public ListHomeMenuBuilder(HomeManager homeManager, ConfigManager configManager){
        this.homeManager = homeManager;
        this.configManager = configManager;
    }
    public Inventory ListHomeGUI(Player player){
        Inventory list = Bukkit.createInventory(new ListHomeHolder(), 54, "Мои дома");
        Set<String> homePlayer = homeManager.getHomeNames(player);
        int slotIndex = 0;
        int[] countPlayerHead = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
        for(String name : homePlayer){
            if(slotIndex >= countPlayerHead.length) break;
            if(homeManager.getHome(player, name) != null){
                double x = homeManager.getHome(player, name).getBlockX();
                double y = homeManager.getHome(player, name).getBlockY();
                double z = homeManager.getHome(player, name).getBlockZ();
                ItemStack headPlayer = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) headPlayer.getItemMeta();
                meta.setOwningPlayer(Bukkit.getOfflinePlayer("Notch "));
                meta.setDisplayName(name);
                List<String> lore = new ArrayList<>();
                lore.add("Координаты: ");
                lore.add("x: " + x);
                lore.add("y: " + y);
                lore.add("z: " + z);
                meta.setLore(lore);

                headPlayer.setItemMeta(meta);


                list.setItem(countPlayerHead[slotIndex], headPlayer);
                slotIndex++;
            }
        }
        ItemStack grayPanel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        int[] countGrayPanel = {0,1,2,3,5,6,7,8,9,18,27,36,46,47,48,50,51,52,53,17,26,35,44};
        for(int i = 0; i <countGrayPanel.length; i++){
            list.setItem(countGrayPanel[i], grayPanel);
        }

        var arrow = configManager.getMenuItem(player,"listmenu", "arrow");
        var limeDye = configManager.getMenuItem(player,"listmenu", "limedye");
        var netherStart = configManager.getMenuItem(player,"listmenu", "nether_star");

        list.setItem(4,netherStart);
        list.setItem(49, limeDye);
         list.setItem(45, arrow);
        player.openInventory(list);
        return list;
    }

}
