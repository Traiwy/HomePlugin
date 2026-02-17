package traiwy.homePlugin.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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
import java.util.Map;


public class ListMenu extends Menu {
    private static final int[] COUNT_PLAYER_HEAD = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34};


    private final MenuService service;

    public ListMenu(MenuService service) {
        super(service);
        this.service = service;

    }

    @Override
    public void setup(Player player) {
        setSlotsHomes(player);
    }

    private void setSlotsHomes(Player player) {
        final List<Home> homes = service.getHomeCache().getAllHome(player.getName());
        System.out.println(homes.size());

        for (int slotIndex = 0; slotIndex < homes.size() && slotIndex < COUNT_PLAYER_HEAD.length; slotIndex++) {
            final Home home = homes.get(slotIndex);
            System.out.println(home.homeName());
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

            final Location location = new Location(Bukkit.getWorld(home.location().world()),
                    home.location().x(),
                    home.location().y(),
                    home.location().z());

            setItem(COUNT_PLAYER_HEAD[slotIndex], new MenuItem(item, e -> {
                player.teleport(location);
            }));
        }

    }
}

