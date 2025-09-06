package command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import util.ConfirmationManagerShareMessagePlayer;
import util.HomeManager;

import java.util.HashMap;
import java.util.UUID;

public class HomeAcceptCommand implements CommandExecutor {
    private final HashMap<UUID, ConfirmationManagerShareMessagePlayer.DoorSelection> transmissionSelection =
            ConfirmationManagerShareMessagePlayer.transmissionSelection;
    private final HomeManager homeManager;

    public HomeAcceptCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return false;

        boolean requestFound = false;

        for (HashMap.Entry<UUID, ConfirmationManagerShareMessagePlayer.DoorSelection> entry :
                ConfirmationManagerShareMessagePlayer.transmissionSelection.entrySet()) {

            ConfirmationManagerShareMessagePlayer.DoorSelection selection = entry.getValue();

            if (player.getName().equalsIgnoreCase(selection.getPlayerName())) {
                requestFound = true;
                UUID senderUUID = entry.getKey();
                Player senderPlayer = Bukkit.getPlayer(senderUUID);
                String homeName = selection.getDoorName();
                if (senderPlayer != null) {
                    Location loc = homeManager.getHome(senderPlayer, homeName);
                    if (loc != null) {
                        homeManager.setHome(player, "shared_" + senderPlayer.getName() + "_" + homeName, loc);
                        player.sendMessage("§aВы приняли доступ к дому '" + homeName + "' от " + senderPlayer.getName());
                        senderPlayer.sendMessage("§aИгрок " + player.getName() + " принял доступ к вашему дому '" + homeName + "'");
                        return true;
                    }
                }
            }
        }
        if (!requestFound) {
            player.sendMessage("§cУ вас нет активных запросов");
        }
        return true;
    }
}
