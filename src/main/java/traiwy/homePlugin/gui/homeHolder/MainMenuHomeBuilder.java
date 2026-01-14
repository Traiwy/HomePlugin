package traiwy.homePlugin.gui.homeHolder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class MainMenuHomeBuilder {
    //private final JavaPlugin plugin;
    //private final ConfigManager configManager;
    //public MainMenuHomeBuilder(JavaPlugin plugin, ConfigManager configManager) {
    //    this.plugin = plugin;
    //    this.configManager = configManager;
    //}
    //public void HomeGUI(Player player){
    //    Inventory home = Bukkit.createInventory(new HomeHolder() , 27, "Дома" );
    //    var elytra = configManager.getMenuItem(player, "mainmenu","elytra");
    //    var apple = configManager.getMenuItem(player, "mainmenu","apple");
    //    var diamont = configManager.getMenuItem(player,"mainmenu","diamond");
    //    home.setItem(11,elytra);
    //    home.setItem(13, apple);
    //    home.setItem(15, diamont);
//
    //    player.openInventory(home);
    //    animateFrame(player, home);
    //}
    //private void animateFrame(Player player, Inventory inv) {
    //    int[] frameSlots = {
    //            0, 1, 2, 3, 4, 5, 6, 7, 8,
    //            17, 26, 25, 24, 23, 22, 21, 20, 19, 18, 9
    //    };
//
    //    new BukkitRunnable() {
    //        int tick = 0;
    //        final int trailLength = 11;
//
    //        @Override
    //        public void run() {
    //            if (!player.getOpenInventory().getTitle().equals("Дома")) {
    //                cancel();
    //                return;
    //            }
//
    //            for (int i = 0; i < frameSlots.length; i++) {
    //                boolean isTrail = false;
//
    //                for (int j = 0; j < trailLength; j++) {
    //                    int index = (tick - j + frameSlots.length) % frameSlots.length;
    //                    if (i == index) {
    //                        isTrail = true;
    //                        break;
    //                    }
    //                }
//
    //                Material material = isTrail
    //                        ? Material.NETHER_STAR
    //                        : Material.GRAY_STAINED_GLASS_PANE;
//
    //                ItemStack pane = new ItemStack(material);
    //                ItemMeta meta = pane.getItemMeta();
    //                meta.setDisplayName(" ");
    //                pane.setItemMeta(meta);
    //                inv.setItem(frameSlots[i], pane);
    //            }
//
    //            tick = (tick + 1) % frameSlots.length;
    //        }
    //    }.runTaskTimer(plugin, 0L, 4L);
    //}

}

