package traiwy.homePlugin.gui.menu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import traiwy.homePlugin.gui.Menu;
import traiwy.homePlugin.gui.button.MenuButton;

public class DeleteMenu extends Menu {
    public static final int[] RED_PANEL = {0,1,2,3,4,5,6,7,8,9,17, 18, 36, 26, 44,46,47, 48, 50, 51, 52, 53, 27, 35};
    public DeleteMenu() {
        super("deletemenu", "Delete Menu", 54);

        for(int i = 0; i < RED_PANEL.length; i++){
            addButton(RED_PANEL[i], new MenuButton(new ItemStack(Material.RED_STAINED_GLASS_PANE), null));
        }
    }
}
