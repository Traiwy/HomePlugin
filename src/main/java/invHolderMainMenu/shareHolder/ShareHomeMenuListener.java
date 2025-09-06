package invHolderMainMenu.shareHolder;

import command.HomeCommand;
import invHolderMainMenu.settingHolder.SettingsHomeMenuBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import util.ConfigManager;
import util.ConfirmationManagerShareHome;
import util.ConfirmationManagerShareMessagePlayer;

import java.sql.Struct;
import java.util.*;

public class ShareHomeMenuListener implements Listener {
    private static final Map<UUID, Boolean> DarkDoorState = new HashMap<>();
    private final SettingsHomeMenuBuilder settingsHomeMenuBuilder;
    private final ConfirmationManagerShareHome confirmationManagerShareHome;
    private final ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer;

    public ShareHomeMenuListener(SettingsHomeMenuBuilder settingsHomeMenuBuilder, ConfirmationManagerShareHome confirmationManagerShareHome, ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer) {
        this.settingsHomeMenuBuilder = settingsHomeMenuBuilder;
        this.confirmationManagerShareHome = confirmationManagerShareHome;
        this.confirmationManagerShareMessagePlayer = confirmationManagerShareMessagePlayer;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if (inventory.getHolder() == null) return;
        if (inventory.getHolder() instanceof ShareHomeHolder && inventory != null && item != null) {
            event.setCancelled(true);
            switch (item.getType()) {
                case ARROW:
                    settingsHomeMenuBuilder.getSettingsGUI(player);
                    break;
                case DARK_OAK_DOOR:
                    String displayName = getDisplayNameAsString(item);
                    player.sendMessage("Выбрано: " + displayName);
                    handleDarkDoor(player, inventory, event.getSlot(), displayName);
                    break;
                case BONE_MEAL:
                    String nameRecipient = HomeCommand.getSharedPlayerName();
                    Player recipient = Bukkit.getPlayer(nameRecipient);
                    UUID playerID = player.getUniqueId();

                    String confirmedHome = confirmationManagerShareHome.getConfirmedHomeName(playerID);
                    if (confirmedHome != null) {
                        confirmationManagerShareMessagePlayer.startSelection(
                                playerID,
                                player.getName(),
                                confirmedHome
                        );
                        recipient.sendMessage("Принять запрос - /homеaccept, отказаться - /homecancel. У вас есть 60 секунд");
                        player.sendMessage("Вы отправили запрос. Ожидайте, пока игрок примет его");
                        player.closeInventory();

                    } else {
                        player.sendMessage("Сначала выберите дом для передачи!");
                    }
            }
        }
    }


    private void handleDarkDoor(Player player, Inventory inventory, int slot, String displayName) {
        UUID targetPlayer = player.getUniqueId();


        if (confirmationManagerShareHome.requiresConfirmation(targetPlayer)) {
            String confirmedHome = confirmationManagerShareHome.getConfirmedHomeName(targetPlayer);
            if (confirmedHome != null && confirmedHome.equals(displayName)) {
                confirmationManagerShareHome.confirm(targetPlayer);
                player.sendMessage("Дом '" + displayName + "' выбран для общего доступа!");
                resetDarkDoor(player, inventory, slot);
                // Реализация отправки запроса
            } else {
                confirmationManagerShareHome.cancelConfirmation(targetPlayer);
                resetDarkDoor(player, inventory, slot);
                player.sendMessage("Подтверждение отменено. Выберите дом заново.");
            }
        } else {
            confirmationManagerShareHome.startConfirmation(targetPlayer, displayName);
            setConfirmationMode(player, inventory, slot, displayName);
            player.sendMessage("Нажмите еще раз для подтверждения выбора дома '" + displayName + "'");
        }
    }

    public void setConfirmationMode(Player player, Inventory inventory, int slot, String displayName) {
        ItemStack darkDoor = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta meta = darkDoor.getItemMeta();

        if (meta != null ) {
            meta.displayName(Component.text(displayName)
                    .color(TextColor.color(0xFFD700)));
            meta.lore(List.of(
                    Component.text("Нажмите еще раз для подтверждения")));
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            darkDoor.setItemMeta(meta);
        }

        inventory.setItem(slot, darkDoor);
        DarkDoorState.put(player.getUniqueId(), true);
    }

    public void resetDarkDoor(Player player, Inventory inventory, int slot) {
        ItemStack darkDoor = new ItemStack(Material.DARK_OAK_DOOR);
        ItemMeta meta = darkDoor.getItemMeta();
        if (meta != null) {
            meta.displayName(); //Добавить описание
            meta.lore(); //Добавить описание

            meta.getEnchants().keySet().forEach(meta::removeEnchant);
            darkDoor.setItemMeta(meta);
        }
        DarkDoorState.remove(player.getUniqueId());
    }

    private String getDisplayNameAsString(ItemStack item) {
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            Component displayNameComponent = meta.displayName();
            if (displayNameComponent == null) {
                return "Без названия";
            }
            return PlainTextComponentSerializer.plainText().serialize(displayNameComponent);
        }
        return "xvk";
    }
}

