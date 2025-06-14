package GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class SecondMenuHome {
    public static void HomeGUI(Player player){
        Inventory home = Bukkit.createInventory(player, 27, "Дома" );

        home.setItem(11, new ItemStack(Material.ELYTRA));
        home.setItem(13, new ItemStack(Material.APPLE));
        home.setItem(15, new ItemStack(Material.DIAMOND));


        player.openInventory(home);
    }
}
