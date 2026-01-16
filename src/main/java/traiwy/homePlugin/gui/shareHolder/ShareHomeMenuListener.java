package traiwy.homePlugin.gui.shareHolder;

import traiwy.homePlugin.gui.settingHolder.SettingsHomeMenuBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
            //switch (item.getType()) {
            //    case ARROW:
            //        settingsHomeMenuBuilder.getSettingsGUI(player);
            //        break;
            //    case DARK_OAK_DOOR:
            //        String displayName = getDisplayNameAsString(item);
            //        handleDarkDoor(player, inventory, event.getSlot(), displayName);
            //        break;
            //    case BONE_MEAL:
                    //String nameRecipient = HomeCommand.getSharedPlayerName();

                    //if (nameRecipient == null || nameRecipient.isEmpty()) {
                    //    player.sendMessage("§cСначала выберите игрока!");
                    //    player.closeInventory();
                    //    return;
                    //}
//
                    //Player recipient = Bukkit.getPlayer(nameRecipient);
                    //if (recipient == null) {
                    //    player.sendMessage("§cИгрок " + nameRecipient + " не в сети!");
                    //    player.closeInventory();
                    //    return;
                    //}

                    UUID playerID = player.getUniqueId();
                    String confirmedHome = confirmationManagerShareHome.getConfirmedHomeName(playerID);

                    if (confirmedHome == null) {
                        player.sendMessage("§cСначала выберите дом для передачи!");
                        player.closeInventory();
                        return;
                    }

                    if (confirmationManagerShareMessagePlayer.hasActiveRequest(playerID)) {
                        player.sendMessage("§cУ вас уже есть активный запрос. Ожидайте 60 секунд");
                        player.closeInventory();
                        return;
                    }
                   //boolean success = confirmationManagerShareMessagePlayer.startSelection(
                   //        playerID,
                   //        nameRecipient,
                   //        confirmedHome
                   //);

                   //if (success) {
                   //    player.sendMessage("§aЗапрос на доступ к дому '" + confirmedHome + "' отправлен игроку " + nameRecipient);
                   //    recipient.sendMessage("§eИгрок " + player.getName() + " хочет предоставить вам доступ к дому '" + confirmedHome + "'");
                   //    recipient.sendMessage("§eПринять запрос - /homeaccept, отказаться - /homecancel");
                   //    recipient.sendMessage("§eУ вас есть 60 секунд на ответ");
                   //    player.closeInventory();
                    //} else {
                    //    player.sendMessage("§cНе удалось отправить запрос. Попробуйте позже.");
                    //    player.closeInventory();
                    //}
                    //break;
            }
        }
    }


    //private void handleDarkDoor(Player player, Inventory inventory, int slot, String displayName) {
    //    UUID targetPlayer = player.getUniqueId();
//
//
    //    if (confirmationManagerShareHome.requiresConfirmation(targetPlayer)) {
    //        String confirmedHome = confirmationManagerShareHome.getConfirmedHomeName(targetPlayer);
    //        if (confirmedHome != null && confirmedHome.equals(displayName)) {
    //            confirmationManagerShareHome.confirm(targetPlayer);
    //            resetDarkDoor(player, inventory, slot);
    //            // Реализация отправки запроса
    //        } else {
    //            confirmationManagerShareHome.cancelConfirmation(targetPlayer);
    //            resetDarkDoor(player, inventory, slot);
    //            player.sendMessage("Подтверждение отменено. Выберите дом заново.");
    //        }
    //    } else {
    //        confirmationManagerShareHome.startConfirmation(targetPlayer, displayName);
    //        setConfirmationMode(player, inventory, slot, displayName);
    //    }
    //}
//
    //public void setConfirmationMode(Player player, Inventory inventory, int slot, String displayName) {
    //    ItemStack darkDoor = new ItemStack(Material.DARK_OAK_DOOR);
    //    ItemMeta meta = darkDoor.getItemMeta();
//
    //    if (meta != null ) {
    //        meta.displayName(Component.text(displayName)
    //                .color(TextColor.color(0xFFD700)));
    //        meta.lore(List.of(
    //                Component.text("Нажмите еще раз для подтверждения")));
    //        meta.addEnchant(Enchantment.LUCK, 1, true);
    //        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    //        darkDoor.setItemMeta(meta);
    //    }
//
    //    inventory.setItem(slot, darkDoor);
    //    DarkDoorState.put(player.getUniqueId(), true);
    //}
//
    //public void resetDarkDoor(Player player, Inventory inventory, int slot) {
    //    ItemStack darkDoor = new ItemStack(Material.DARK_OAK_DOOR);
    //    ItemMeta meta = darkDoor.getItemMeta();
    //    if (meta != null) {
    //        meta.displayName(); //Добавить описание
    //        meta.lore(); //Добавить описание
//
    //        meta.getEnchants().keySet().forEach(meta::removeEnchant);
    //        darkDoor.setItemMeta(meta);
    //    }
    //    DarkDoorState.remove(player.getUniqueId());
    //}
//
    //private String getDisplayNameAsString(ItemStack item) {
    //    if (item != null) {
    //        ItemMeta meta = item.getItemMeta();
    //        Component displayNameComponent = meta.displayName();
    //        if (displayNameComponent == null) {
    //            return "Без названия";
    //        }
    //        return PlainTextComponentSerializer.plainText().serialize(displayNameComponent);
    //    }
    //    return "xvk";
    //}


