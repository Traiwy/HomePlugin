package traiwy.homePlugin.gui.settingHolder;

import traiwy.homePlugin.gui.delayHolder.DelayMenuBuilder;
import traiwy.homePlugin.gui.homeHolder.MainMenuHomeBuilder;
import traiwy.homePlugin.gui.shareHolder.ShareHomeMenuBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import traiwy.homePlugin.util.ConfirmationManagerDeleteHome;
import traiwy.homePlugin.util.HomeManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SettingsHomeListener implements Listener {
    private final MainMenuHomeBuilder mainMenuHomeBuilder;
    private final ConfirmationManagerDeleteHome confirmationManagerDeleteHome;
    private final JavaPlugin plugin;
    private final HomeManager homeManager;
    private final ShareHomeMenuBuilder shareHomeMenuBuilder;

    private static final Map<UUID, Boolean> tripwireHookState = new HashMap<>();
    private static final Map<UUID, Boolean> redstoneBlockState = new HashMap<>();

    public SettingsHomeListener(MainMenuHomeBuilder mainMenuHomeBuilder, ConfirmationManagerDeleteHome confirmationManagerDeleteHome, JavaPlugin plugin, HomeManager homeManager, ShareHomeMenuBuilder shareHomeMenuBuilder) {
        this.mainMenuHomeBuilder = mainMenuHomeBuilder;
        this.confirmationManagerDeleteHome = confirmationManagerDeleteHome;
        this.plugin = plugin;
        this.homeManager = homeManager;
        this.shareHomeMenuBuilder = shareHomeMenuBuilder;
        new BukkitRunnable() {
            @Override
            public void run() {
                ConfirmationManagerDeleteHome.cleanupExpired();
            }
        }.runTaskTimer(plugin, 1200L, 1200L);
    }


    @EventHandler
    public void onSettingsHome(InventoryClickEvent e) {
        var inv = e.getClickedInventory();
        var player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

       //if (inv != null && inv.getHolder() instanceof SettingsHomeHolder) {
       //    if (item != null) {
       //        e.setCancelled(true);
       //        switch (item.getType()) {
       //            case CLOCK:
       //                DelayMenuBuilder.DelayGUI(player);
       //                break;
       //            case TRIPWIRE_HOOK:
       //                toggleTripwiteHook(player, inv, e.getSlot());
       //                break;
       //            case PLAYER_HEAD:
       //                break;
       //            case REDSTONE_BLOCK:
       //                handleRedstoneBlockClick(player, inv, e.getSlot());
       //                break;
       //            case REPEATER:
       //                break;
       //            case ARROW:
       //                mainMenuHomeBuilder.HomeGUI(player);
       //        }
       //    }
       //}
    }

    private void toggleTripwiteHook(Player player, Inventory inventory, int slot) {
        UUID playerID = player.getUniqueId();
        boolean isEnchanted = tripwireHookState.getOrDefault(playerID, false);

        ItemStack tripwireHook = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = tripwireHook.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("");
            if (!isEnchanted) {
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            tripwireHook.setItemMeta(meta);
        }
        inventory.setItem(slot, tripwireHook);
        tripwireHookState.put(playerID, !isEnchanted);
    }

    public static ItemStack getTripwireHookState(Player player) {
        boolean isEnchanted = tripwireHookState.getOrDefault(player.getUniqueId(), false);
        ItemStack tripwireHook = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = tripwireHook.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("");
            if (isEnchanted) {
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            tripwireHook.setItemMeta(meta);
        }
        return tripwireHook;
    }

    private void handleRedstoneBlockClick(Player player, Inventory inventory, int slot) {
        UUID playerId = player.getUniqueId();

        if (ConfirmationManagerDeleteHome.requiresConfirmation(playerId)) {
            ConfirmationManagerDeleteHome.cancelConfirmation(playerId);
            homeManager.deleteAllHomes(player);
            resetRedstoneBlock(player, inventory, slot);
            player.sendMessage(Component.text("Все дома успешно удалены!")
                    .color(TextColor.color(0x00FF00)));
        } else {
            ConfirmationManagerDeleteHome.startConfirmation(playerId);
            setConfirmationMode(player, inventory, slot);
            player.sendMessage(Component.text("Нажмите еще раз для подтверждения удаления всех домов!")
                    .color(TextColor.color(0xFFA500)));
        }
    }

    private void setConfirmationMode(Player player, Inventory inventory, int slot) {
        ItemStack redstoneBlock = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = redstoneBlock.getItemMeta();

        if (meta != null) {
            meta.displayName(Component.text("✦ Подтвердите удаление всех домов")
                    .color(TextColor.color(0xFFA500))
                    .decorate(TextDecoration.BOLD));

            meta.lore(List.of(
                    Component.text("Нажмите еще раз чтобы подтвердить")
                            .color(TextColor.color(0xFFFFFF)),
                    Component.text("Действие нельзя отменить!")
                            .color(TextColor.color(0xFF0000))
            ));

            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            redstoneBlock.setItemMeta(meta);
        }

        inventory.setItem(slot, redstoneBlock);
        redstoneBlockState.put(player.getUniqueId(), true);
    }

    private void resetRedstoneBlock(Player player, Inventory inventory, int slot) {
        ItemStack redstoneBlock = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = redstoneBlock.getItemMeta();

        if (meta != null) {
            meta.displayName(Component.text("✦ Удалить все дома")
                    .color(TextColor.color(0xFF0000)));

            meta.lore(null);
            meta.getEnchants().keySet().forEach(meta::removeEnchant);
            redstoneBlock.setItemMeta(meta);
        }

        inventory.setItem(slot, redstoneBlock);
        redstoneBlockState.put(player.getUniqueId(), false);
    }

    public static ItemStack getRedstoneBlock(Player player) {
        boolean isEnchanted = redstoneBlockState.getOrDefault(player.getUniqueId(), false);
        ItemStack redstoneBlock = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = redstoneBlock.getItemMeta();

        if (meta != null) {
            meta.displayName(Component.text("✦ Удалить все дома")
                    .color(TextColor.color(0xFF0000)));

            if (isEnchanted) {
                meta.addEnchant(Enchantment.LUCK, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            redstoneBlock.setItemMeta(meta);
        }
        return redstoneBlock;
    }
}
