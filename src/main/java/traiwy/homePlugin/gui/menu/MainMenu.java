package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuItem;
import traiwy.homePlugin.gui.service.MenuService;
import traiwy.homePlugin.util.ItemBuilder;

public class MainMenu extends Menu {
    public static final int[] GRAY_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18,19,20,21,22,23,24,25,26};
    private final MenuService service;


    public MainMenu(MenuService service) {
        super("meinnmenu", "Main menu", 27);
        this.service = service;
    }

    @Override
    public void setup(Player player) {

        var menuConfig = service.getCfgData().menus().get("main");

        for(int i = 0; i < GRAY_PANEL.length; i++){
            setItem(GRAY_PANEL[i], new MenuItem(new ItemStack(Material.GRAY_STAINED_GLASS_PANE), null));
        }

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

