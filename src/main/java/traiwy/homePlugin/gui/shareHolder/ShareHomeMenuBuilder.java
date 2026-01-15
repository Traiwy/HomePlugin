package traiwy.homePlugin.gui.shareHolder;

public class ShareHomeMenuBuilder {

   //private final HomeManager homeManager;
   //private final ConfigManager configManager;

   //public ShareHomeMenuBuilder(HomeManager homeManager, ConfigManager configManager) {
   //    this.homeManager = homeManager;
   //    this.configManager = configManager;
   //}

   //public void getShareHomeMenu(Player player) {
   //    Inventory inventory = Bukkit.createInventory(new ShareHomeHolder(), 54, "Режим выбора");
   //    Set<String> homePlayer = homeManager.getHomeNames(player);
   //    int slotIndex = 0;
   //    int[] countDarkBoat = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43};
   //    for (String name : homePlayer) {
   //        if (slotIndex >= countDarkBoat.length) break;
   //        if (homeManager.getHome(player, name) != null) {
   //            double x = homeManager.getHome(player, name).getBlockX();
   //            double y = homeManager.getHome(player, name).getBlockY();
   //            double z = homeManager.getHome(player, name).getBlockZ();
   //            ItemStack darkDoor = new ItemStack(Material.DARK_OAK_DOOR);
   //            ItemMeta meta = (ItemMeta) darkDoor.getItemMeta();
   //            meta.setDisplayName(name);
   //            List<String> lore = new ArrayList<>();
   //            lore.add("Координаты: ");
   //            lore.add("x: " + x);
   //            lore.add("y: " + y);
   //            lore.add("z: " + z);
   //            meta.setLore(lore);

   //            darkDoor.setItemMeta(meta);
   //            inventory.setItem(countDarkBoat[slotIndex], darkDoor);
   //            slotIndex++;
   //        }
   //    }
   //    ItemStack grayPanel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
   //    ItemStack paper = new ItemStack(Material.PAPER);
   //    ItemStack boneMeal = new ItemStack(Material.BONE_MEAL);
   //    ItemMeta metaBone = boneMeal.getItemMeta();
   //    metaBone.displayName(Component.text("Нажмите, чтобы подтвердить свой выбор")
   //            .color(TextColor.color(255, 0, 64))
   //            .decoration(TextDecoration.ITALIC, false));
   //    ItemMeta meta = paper.getItemMeta();
   //    meta.displayName(Component.text("Режим выбора")
   //            .color(TextColor.color(0, 109, 255))
   //            .decoration(TextDecoration.ITALIC, false)
   //            .decoration(TextDecoration.BOLD, true));
   //    meta.lore(List.of(
   //            Component.text("Режим, в котором вам нужно выбрать точку ")
   //                    .color(TextColor.color(25, 93, 184))
   //                    .decoration(TextDecoration.BOLD, true)
   //                    .decoration(TextDecoration.ITALIC, false),
   //            Component.text("дома, которая будет общей с вашим другом")
   //                    .color(TextColor.color(25, 93, 184))
   //                    .decoration(TextDecoration.BOLD, true)
   //                    .decoration(TextDecoration.ITALIC, false)));
   //    paper.setItemMeta(meta);
   //    boneMeal.setItemMeta(metaBone);
   //    int[] countGrayPanel = {0, 1, 2, 3, 5, 6, 7, 8, 9, 18, 27, 36, 46, 47, 48, 50, 51, 52, 53, 17, 26, 35, 44};
   //    for (int i = 0; i < countGrayPanel.length; i++) {
   //        inventory.setItem(countGrayPanel[i], grayPanel);
   //    }

   //    var arrow = configManager.getMenuItem(player, "listmenu", "arrow");
   //    inventory.setItem(45, arrow);
   //    inventory.setItem(4, paper);
   //    inventory.setItem(49, boneMeal);
   //    player.openInventory(inventory);
   //}
}
