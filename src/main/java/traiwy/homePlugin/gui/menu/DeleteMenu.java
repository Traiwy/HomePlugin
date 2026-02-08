package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.util.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class DeleteMenu extends Menu {
    private final MenuService service;
    public static final int[] RED_PANEL = {0,1,2,3,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53, 27, 35};
    private static final int[] COUNT_PLAYER_HEAD = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34};
    public DeleteMenu(MenuService service) {
        super("deletemenu", "Delete Menu", 54);

        this.service = service;
    }

    @Override
    public void setup(Player player) {
        final List<Home> homes = service.getHomeCache().getAllHome(player.getName());

        for (int slot : RED_PANEL) {
            if (!getItems().containsKey(slot)) {
                setItem(slot, new MenuItem(
                        new ItemStack(Material.RED_STAINED_GLASS_PANE), null
                ));
            }
        }

        var menuConfig = service.getCfgData().menus().get("delete");
        menuConfig.layout().forEach((slot, itemId) -> {
            var itemCfg = service.getCfgData().items().get(itemId);
            ItemBuilder builder = ItemBuilder.of(Material.valueOf(itemCfg.material()));
            if (itemCfg.name() != null)
                builder.name(itemCfg.name());
            if (itemCfg.lore() != null)
                builder.lore(itemCfg.lore());
            final ItemStack item = builder.build();
            setItem(slot, new MenuItem(item, e ->{
                service.getMenuActionRegistry().execute(itemId, player);
            }
            ));
        });


        for (int slotIndex = 0; slotIndex < homes.size() && slotIndex < COUNT_PLAYER_HEAD.length; slotIndex++) {
            Home home = homes.get(slotIndex);
            final List<String> lore = new ArrayList<>();

            lore.add(" ");
            lore.add("§b❙ §fВладелец: §b" + home.ownerName());

            List<Member> members = service.getHomeFacade().getMembers(home);
            if (!members.isEmpty()) {
                final List<String> memberNames = new ArrayList<>();
                for (Member member : members) memberNames.add(member.name());
                lore.add("§b❙ §fУчастники: §b" + String.join(", ", memberNames));
            }

            lore.add(" ");
            lore.add("§b❙ §fКоординаты:");
            lore.add("§7  World: §b" + home.location().world());
            lore.add("§7  X: §b" + home.location().x());
            lore.add("§7  Y: §b" + home.location().y());
            lore.add("§7  Z: §b" + home.location().z());
            lore.add(" ");

            final ItemStack item = ItemBuilder.of(Material.PLAYER_HEAD)
                    .name(home.homeName())
                    .lore(lore)
                    .build();

            final int slot = COUNT_PLAYER_HEAD[slotIndex];
            setItem(slot, new MenuItem(item, e -> {
                service.getHomeCache().remove(player.getName(), home);
                player.getOpenInventory().getTopInventory().setItem(slot, null);
            }));
        }

        setItem(49, new  MenuItem(new ItemStack(Material.RED_DYE), InventoryClickEvent -> {
            player.openInventory(service.getListMenu().getInventory());
        }));
    }
}
