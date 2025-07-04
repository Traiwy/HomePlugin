package invHolderMainMenu.delayHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DelayMenuBuilder {
    public static void DelayGUI(Player player){
        var inv = Bukkit.createInventory(new DelayHolder(), 27, "Задержка");

        var raising = new ItemStack(Material.BARRIER);
        var downgrade = new ItemStack(Material.BARRIER);
        var clock = new ItemStack(Material.CLOCK);

        var arrow = new ItemStack(Material.ARROW);

        inv.setItem(18, arrow);

        inv.setItem(11,raising);
        inv.setItem(15, downgrade);
        inv.setItem(13,clock);

        player.openInventory(inv);
    }
}
