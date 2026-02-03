package traiwy.homePlugin.gui.menu.settings;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class SettingsMenu extends Menu{
    public static final int[] GRAY_PANEL = {0,9,18,27,36,8,17,26,35,44,53,46,47,48,49,50,51,52};
    private final MenuService service;

    public SettingsMenu(MenuService service) {
        super("settingsmenu", "Settings Menu", 54);
        this.service = service;
    }

    @Override
    public void setup(Player player) {
        final var menuConfig = service.getCfgData().menus().get("settings");

        for(int j : GRAY_PANEL) {
            setItem(j, new MenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));

            menuConfig.layout().forEach((slot, itemId) -> {
                final var itemCfg = service.getCfgData().items().get(itemId);
                final ItemBuilder builder = ItemBuilder.of(Material.valueOf(itemCfg.material()));
                if(itemCfg.name() != null) {
                    builder.name(itemCfg.name());
                }
                if(itemCfg.lore() != null) {
                    builder.lore(itemCfg.lore());
                }

                final ItemStack item = builder.build();
                setItem(slot, new MenuItem(item, e ->
                        service.getMenuActionRegistry().execute(itemId, player)));
            });
        }
    }

}
