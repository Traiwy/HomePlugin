package invHolderMainMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class MainMenuHome {
    public static void HomeGUI(Player player){
        Inventory home = Bukkit.createInventory(new CustomMenuHolder() , 27, "Дома" );

        ItemStack elytra = new ItemStack(Material.ELYTRA);
        ItemStack apple = new ItemStack(Material.APPLE);
        ItemStack diamond = new ItemStack(Material.DIAMOND);

        ItemMeta elytraMeta = elytra.getItemMeta();
        ItemMeta appleMeta = apple.getItemMeta();
        ItemMeta diamondMeta = diamond.getItemMeta();

        elytraMeta.setDisplayName("§b▪▪▪▪§r §aСоздание точки дома §b▪▪▪▪");
        appleMeta.setDisplayName("§b▪▪▪▪§r §aТелепортация к вашей точке дома §b▪▪▪▪");
        diamondMeta.setDisplayName("§b▪▪▪▪§r §cУдаление точки дома §b▪▪▪▪");


        apple.setItemMeta(appleMeta);
        elytra.setItemMeta(elytraMeta);
        diamond.setItemMeta(diamondMeta);

        home.setItem(11, elytra);
        home.setItem(13, apple);
        home.setItem(15,diamond);


        player.openInventory(home);
    }
}
