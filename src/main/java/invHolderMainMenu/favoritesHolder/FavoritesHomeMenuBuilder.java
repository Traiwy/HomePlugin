package invHolderMainMenu.favoritesHolder;

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

public class FavoritesHomeMenuBuilder {
    private final HomeManager homeManager;
    public FavoritesHomeMenuBuilder(HomeManager homeManager){
        this.homeManager = homeManager;
    }
    public void getFavoriteHomeMenu(Player player){
        Inventory inv = Bukkit.createInventory(new FavoritesHomeMenuHolder(),54,"Избранные дома");
        Set<String> homePlayer = homeManager.getHomeNames(player);
        int slotIndex =0;
        int[] countPlayerHead = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
        for(String name:homePlayer){
            if(slotIndex >= countPlayerHead.length) break;
            if(homeManager.getHome(player,name) != null){
                ItemStack headPlayer = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) headPlayer.getItemMeta();
                meta.setDisplayName(name);
                List<String> lore = new ArrayList<>();
                lore.add("§e✦ Нажмите §fSHIFT§7 + §fПКМ§7, чтобы §cдобавить ваш дом в избранные");
                meta.setLore(lore);

                headPlayer.setItemMeta(meta);

                inv.setItem(countPlayerHead[slotIndex],headPlayer);
                slotIndex++;
            }
        }
        var arrow = new ItemStack(Material.ARROW);
        var greenDye = new ItemStack(Material.GREEN_DYE);
        var redstone = new ItemStack(Material.REDSTONE);
        inv.setItem(4,redstone);
        inv.setItem(49, greenDye);
        inv.setItem(45, arrow);
        player.openInventory(inv);

        ItemStack pinkPanel = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        int[] countPinkPanel = {0,1,2,3,5,6,7,8,9,18,27,36,46,47,48,50,51,52,53,17,26,35,44};
        for(int i = 0; i <countPinkPanel.length; i++){
            inv.setItem(countPinkPanel[i], pinkPanel);
        }

    }
}
