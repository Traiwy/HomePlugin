package traiwy.homePlugin.gui.menu.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class DeleteAllMenu extends Menu {
    private final MenuService service;
    public DeleteAllMenu( MenuService service) {
        super("remove_menu", "Remove Menu", 27);
        this.service = service;
    }

    @Override
    public void setup(Player player) {
        final var menuConfig = service.getCfgData().menus().get("delete_all_home");

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
    }
}
