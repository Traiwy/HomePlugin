package traiwy.homePlugin.gui.settingHolder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SettingsHomeMenuBuilder {
    //private final ConfigManager configManager;
    //public SettingsHomeMenuBuilder(ConfigManager configManager){
    //    this.configManager = configManager;
    //}
    //public void getSettingsGUI(Player player) {
    //    Inventory inv = Bukkit.createInventory(new SettingsHomeHolder(), 27, "Настройки");
//
    //    var clock = configManager.getMenuItem(player, "settingsmenu", "clock");
    //    var player_head = configManager.getMenuItem(player, "settingsmenu", "player_head");
    //    var arrow = configManager.getMenuItem(player, "settingsmenu", "arrow");
//
    //    ItemMeta meta = player_head.getItemMeta();
    //    meta.displayName(Component.text("✦ Поделиться с другом")
    //            .color(TextColor.color(0, 255, 251))
    //            .decoration(TextDecoration.ITALIC, false));
    //    meta.lore(List.of(Component.text("Введите команду /home share <ник игрока>, чтобы поделиться точкой дома")));
//
//
    //    player_head.setItemMeta(meta);
    //    inv.setItem(10, clock);
    //    inv.setItem(12, SettingsHomeListener.getTripwireHookState(player));
    //    inv.setItem(14, player_head);
    //    inv.setItem(16, SettingsHomeListener.getRedstoneBlock(player));
    //    inv.setItem(18, arrow);
//
//
    //    ItemStack grayPanel = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    //    int[] countGrayPanel = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19, 20, 21, 22, 23, 24, 25, 26, 17};
    //    for (int i = 0; i < countGrayPanel.length; i++) {
    //        inv.setItem(countGrayPanel[i], grayPanel);
    //    }
//
    //    player.openInventory(inv);
//
    //}
}
