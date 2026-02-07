package traiwy.homePlugin.gui.menu.choose;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.command.impl.invite.context.InviteContext;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.home.Home;
import traiwy.homePlugin.home.Member;
import traiwy.homePlugin.manager.RequestManager;
import traiwy.homePlugin.share.Request;
import traiwy.homePlugin.util.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class ChooseHomeMenu extends Menu {
    private static final int[] GREEN_PANEL = {0, 1, 2, 3,5, 6, 7, 8, 9, 17, 18, 36, 26, 44, 46, 47, 48, 50, 51, 52, 53};
    private static final int[] COUNT_PLAYER_HEAD = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};

    private final MenuService service;

    public ChooseHomeMenu(MenuService service) {
        super("choose_menu", "Choose Menu", 54);
        this.service = service;
    }

    @Override
    public void setup(Player player) {
        var menuConfig = service.getCfgData().menus().get("list");


        menuConfig.layout().forEach((slot, itemId) -> {
            var itemCfg = service.getCfgData().items().get(itemId);
            ItemBuilder builder = ItemBuilder.of(Material.valueOf(itemCfg.material()));
            if (itemCfg.name() != null)
                builder.name(itemCfg.name());
            if (itemCfg.lore() != null)
                builder.lore(itemCfg.lore());
            final ItemStack item = builder.build();
            setItem(slot, new MenuItem(item, e ->
                    service.getMenuActionRegistry().execute(itemId, player)
            ));
        });

        for (int i = 0; i < GREEN_PANEL.length; i++) {
            setItem(GREEN_PANEL[i], new MenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }
        setSlotsHomes(player);
    }

    private void setSlotsHomes(Player player) {
        final List<Home> homes = service.getCacheHome().getAllHome(player.getName());
        System.out.println(homes.size());

        for (int slotIndex = 0; slotIndex < homes.size() && slotIndex < COUNT_PLAYER_HEAD.length; slotIndex++) {
            final Home home = homes.get(slotIndex);
            System.out.println(home.homeName());
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
                service.getClickHomeManager().addHome(player, home);
                final InviteContext context = service.getInviteContextManager().get(player);
                final Request request = new Request(context.sender(), context.receiver(), 100, home);
                service.getRequestManager().addRequest(request);
                context.sender().sendMessage("Вы отправили заявку");
                context.receiver().sendMessage("Вам отправили заявку, чтобы её принять пропиши /home accept");
                getInventory().close();
            }));
        }

    }
}
