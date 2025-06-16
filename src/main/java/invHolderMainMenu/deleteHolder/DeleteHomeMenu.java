package invHolderMainMenu.deleteHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import util.HomeManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeleteHomeMenu {
    private final HomeManager homeManager;
    public DeleteHomeMenu(HomeManager homeManager){
        this.homeManager = homeManager;
    }
    public Inventory DeleteHomeGUI(Player player){
        Inventory delete = Bukkit.createInventory(new DeleteMenuHolder(), 54, "Режим удаления");
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
                lore.add("§e✦ Нажмите §fSHIFT§7 + §fПКМ§7, чтобы §cудалить ваш дом");
                meta.setLore(lore);

                headPlayer.setItemMeta(meta);


                delete.setItem(countPlayerHead[slotIndex], headPlayer);
                slotIndex++;
            }
        }
        ItemStack pinkPanel = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        int[] countPinkPanel = {0,1,2,3,4,5,6,7,8,9,18,27,36,46,47,48,50,51,52,53,17,26,35,44};
        for(int i = 0; i <countPinkPanel.length; i++){
            delete.setItem(countPinkPanel[i], pinkPanel);
        }

        ItemStack redDye = new ItemStack(Material.RED_DYE);
        ItemStack arrow = new ItemStack(Material.ARROW);

        delete.setItem(45, arrow);
        delete.setItem(49, redDye);
        player.openInventory(delete);
        return delete;
    }
}
