package traiwy.homePlugin.gui.menu;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.cache.home.CacheHome;
import traiwy.homePlugin.config.data.ConfigData;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;
import traiwy.homePlugin.gui.button.action.OpenMenuAction;
import traiwy.homePlugin.util.ItemFactory;


public class ListMenu extends Menu {
    public static final int[] GRAY_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53};

    private final CacheHome cacheHome;
    public ListMenu(ConfigData configData, Menu settingsMenu, CacheHome cacheHome) {
        super("listmenu", "List Home", 54);
        this.cacheHome = cacheHome;

        var listMenuItems = configData.menu().get(getId()).items();

        getButtons().put(45, new MenuButton(
                ItemFactory.create(listMenuItems.get("arrow")),
                null
        ));

        getButtons().put(49, new MenuButton(
                ItemFactory.create(listMenuItems.get("limedye")),
                null
        ));

        getButtons().put(4, new MenuButton(
                ItemFactory.create(listMenuItems.get("nether_star")),
                new OpenMenuAction(settingsMenu)
        ));

        for(int i = 0; i < GRAY_PANEL.length; i++){
            addButton(GRAY_PANEL[i], new MenuButton(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }
    }

    //public Inventory ListHomeGUI(Player player){
    //    Inventory list = Bukkit.createInventory(new ListHomeHolder(), 54, "Мои дома");
    //    Set<String> homePlayer = homeManager.getHomeNames(player);
    //    int slotIndex = 0;
    //    int[] countPlayerHead = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
    //    for(String name : homePlayer) {
    //        if (slotIndex >= countPlayerHead.length) break;
    //        if (homeManager.getHome(player, name) != null) {
    //            int x = homeManager.getHome(player, name).getBlockX();
    //            int y = homeManager.getHome(player, name).getBlockY();
    //            int z = homeManager.getHome(player, name).getBlockZ();
    //            String owner = homeManager.getOwner(player, name);
    //            List<String> member = homeManager.getMember(player, name);
    //            ItemStack headPlayer = new ItemStack(Material.PLAYER_HEAD);
//
    //            SkullMeta meta = (SkullMeta) headPlayer.getItemMeta();
    //            meta.setOwningPlayer(Bukkit.getOfflinePlayer("Notch "));
    //            meta.displayName(Component.text(name)
    //                    .decoration(TextDecoration.ITALIC, false));
    //            List<Component> lore = new ArrayList<>();
    //            lore.add(TextUtil.descripthion("§b❙ §fВладелец: " + "§b" + owner));
//
//
    //            if (member != null && !member.isEmpty()) {
    //                String members = String.join(", ", member);
    //                lore.add(TextUtil.descripthion("§b❙ §fУчастники: " + "§b" + members));
    //            }
    //            lore.add(TextUtil.descripthion("§b❙ §fКоординаты: " + "§b" + x + ", " + y +", " + z+"."));
    //            meta.lore(lore);
//
    //            headPlayer.setItemMeta(meta);
//
    //            list.setItem(countPlayerHead[slotIndex], headPlayer);
    //            slotIndex++;
    //        }
    //    }
    //    ItemStack grayPanel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    //    int[] countGrayPanel = {0,1,2,3,5,6,7,8,9,18,27,36,46,47,48,50,51,52,53,17,26,35,44};
    //    for(int i = 0; i <countGrayPanel.length; i++){
    //        list.setItem(countGrayPanel[i], grayPanel);
    //    }
//
    //    var arrow = configManager.getMenuItem(player,"listmenu", "arrow");
    //    var limeDye = configManager.getMenuItem(player,"listmenu", "limedye");
    //    var netherStart = configManager.getMenuItem(player,"listmenu", "nether_star");
//
    //    list.setItem(4,netherStart);
    //    list.setItem(49, limeDye);
    //     list.setItem(45, arrow);
    //    player.openInventory(list);
    //    return list;
    //}

}

