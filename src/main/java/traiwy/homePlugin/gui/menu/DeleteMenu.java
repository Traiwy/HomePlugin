package traiwy.homePlugin.gui.menu;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.configuration.IconConfiguration;
import traiwy.homePlugin.configuration.MenuConfiguration;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.util.ItemBuilder;
import traiwy.homePlugin.util.PlaceholderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteMenu extends Menu {
    private final MenuService service;
    private static final int[] COUNT_PLAYER_HEAD = {10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34};
    public DeleteMenu(MenuService service) {
        super(service);
        this.service = service;
    }

    @Override
    public void setup(Player player) {
        final List<Home> homes = service.getHomeCache().getAllHome(player.getName());
        final MenuConfiguration menu = service.getCfgData().getConfiguration().menus().get("delete");
        final IconConfiguration icon = menu.icons().get("home");

        for (int i = 0; i < homes.size() && i < dynamicSlots.size(); i++) {

            final int slot = dynamicSlots.get(i);
            final Home home = homes.get(i);

            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("home", home.homeName());
            placeholders.put("owner", home.ownerName());
            placeholders.put("world", home.location().world());
            placeholders.put("x", String.valueOf(home.location().x()));
            placeholders.put("y", String.valueOf(home.location().y()));
            placeholders.put("z", String.valueOf(home.location().z()));

            List<Member> members = service.getHomeFacade().getMembers(home);
            placeholders.put("members",
                    members.isEmpty()
                            ? "Нет"
                            : members.stream().map(Member::name).collect(Collectors.joining(", "))
            );

            List<String> lore = icon.lore().stream()
                    .map(line -> PlaceholderUtil.apply(line, placeholders))
                    .toList();

            String name = PlaceholderUtil.apply(icon.name(), placeholders);

            ItemStack item = ItemBuilder.of(Material.PLAYER_HEAD)
                    .name(name)
                    .lore(lore)
                    .build();


            setItem(slot, new MenuItem(item, e -> {
                service.getHomeCache().remove(player.getName(), home);
                player.getOpenInventory().getTopInventory().setItem(slot, null);
            }));
        }

    }
}
