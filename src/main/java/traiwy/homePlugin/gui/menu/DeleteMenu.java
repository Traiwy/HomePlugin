package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;

public class DeleteMenu extends Menu {
    public static final int[] RED_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53, 27, 35};
    public DeleteMenu() {
        super("deletemenu", "Delete Menu", 54);

        for (int i = 0; i < RED_PANEL.length; i++) {
            addButton(RED_PANEL[i], new MenuButton(new ItemStack(Material.RED_STAINED_GLASS_PANE), null));
        }

        //public Inventory DeleteHomeGUI(Player player) {
        //    Inventory delete = Bukkit.createInventory(new DeleteMenuHolder(), 54, "Режим удаления");
        //    Set<String> homePlayer = homeManager.getHomeNames(player);
        //    int slotIndex = 0;
        //    int[] countPlayerHead = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};
        //    for (String name : homePlayer) {
        //        if (slotIndex >= countPlayerHead.length) break;
        //        if (homeManager.getHome(player, name) != null) {
        //            ItemStack headPlayer = new ItemStack(Material.PLAYER_HEAD);
        //            SkullMeta meta = (SkullMeta) headPlayer.getItemMeta();
        //            meta.setOwningPlayer(Bukkit.getOfflinePlayer("Notch "));
        //            meta.setDisplayName(name);
        //            List<String> lore = new ArrayList<>();
        //            lore.add("§e✦ Нажмите §fCTRL§7 + §fSHIFT§7 + §fПКМ§7, чтобы §cудалить ваш дом");
        //            meta.setLore(lore);
//
        //            headPlayer.setItemMeta(meta);
//
//
        //            delete.setItem(countPlayerHead[slotIndex], headPlayer);
        //            slotIndex++;
        //        }
        //    }
        //    ItemStack redPanel = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        //    int[] countRedPanel = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 18, 27, 36, 46, 47, 48, 50, 51, 52, 53, 17, 26, 35, 44, 45};
        //    for (int i = 0; i < countRedPanel.length; i++) {
        //        delete.setItem(countRedPanel[i], redPanel);
        //    }
        //    var redDye = configManager.getMenuItem(player, "deletemenu", "reddye");
        //    player.openInventory(delete);
        //    animateFrame(player, delete);
        //    return delete;
        //}
//
        //private void animateFrame(Player player, Inventory inv) {
        //    List<Integer> borderSlots = Arrays.asList(
        //            0, 1, 2, 3, 4, 5, 6, 7, 8,
        //            17, 26, 35, 44,
        //            53, 52, 51, 50, 48, 47, 46,
        //            36, 27, 18, 9
        //    );
//
        //    ItemStack redGlass = createPane(Material.RED_STAINED_GLASS_PANE);
        //    ItemStack blackGlass = createPane(Material.BLACK_STAINED_GLASS_PANE);
//
        //    new BukkitRunnable() {
        //        int tick = 0;
//
        //        @Override
        //        public void run() {
        //            if (!player.getOpenInventory().getTitle().equals("Режим удаления")) {
        //                cancel();
        //                return;
        //            }
//
        //            for (int i = 0; i < borderSlots.size(); i++) {
        //                int slot = borderSlots.get(i);
        //                ItemStack item = ((i + tick) % 2 == 0) ? redGlass : blackGlass;
        //                inv.setItem(slot, item);
        //            }
//
        //            tick = (tick + 1) % 2;
        //        }
        //    }.runTaskTimer(plugin, 0L, 10L);
        //}
//
        //private ItemStack createPane(Material material) {
        //    ItemStack pane = new ItemStack(material);
        //    var meta = pane.getItemMeta();
        //    if (meta != null) {
        //        meta.setDisplayName(" ");
        //        pane.setItemMeta(meta);
        //    }
        //    return pane;
        //
    }

}
