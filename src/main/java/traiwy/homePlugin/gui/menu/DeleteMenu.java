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
    public static final int[] RED_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53, 27, 35};
    private static final int[] COUNT_PLAYER_HEAD = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
    public DeleteMenu(MenuService service) {
        super("deletemenu", "Delete Menu", 54);

        this.service = service;
    }

    @Override
    public void setup(Player player) {
        final List<Home> homes = service.getCacheHome().getAllHome(player.getName());

        for (int slotIndex = 0; slotIndex < homes.size() && slotIndex < COUNT_PLAYER_HEAD.length; slotIndex++) {
            Home home = homes.get(slotIndex);
            final List<String> lore = new ArrayList<>();

            lore.add(" ");
            lore.add("§b❙ §fВладелец: §b" + home.ownerName());

            if (!home.members().isEmpty()) {
                final List<String> memberNames = new ArrayList<>();
                for (Member member : home.members()) {
                    if (member != null) memberNames.add(member.name());
                }
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

            setItem(COUNT_PLAYER_HEAD[slotIndex], new MenuItem(item, e -> {
                // TODO: обработка клика
            }));
        }

        for (int i = 0; i < RED_PANEL.length; i++) {
            setItem(RED_PANEL[i], new MenuItem(new ItemStack(Material.RED_STAINED_GLASS_PANE), null));
        }

        setItem(49, new  MenuItem(new ItemStack(Material.RED_DYE), InventoryClickEvent -> {
            player.openInventory(service.getListMenu().getInventory());
        }));
    }

}
