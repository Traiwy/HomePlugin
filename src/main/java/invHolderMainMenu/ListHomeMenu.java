package invHolderMainMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ListHomeMenu {
    public void ListHomeGUI(Player player){
        Inventory list = Bukkit.createInventory(new CustomMenuHolder(), 54, "Мои дома");
        ItemStack arrow = new ItemStack(Material.ARROW);

        list.setItem(45, arrow);
        player.openInventory(list);
    }
}
