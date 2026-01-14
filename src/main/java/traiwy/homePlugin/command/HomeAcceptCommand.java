package traiwy.homePlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.util.ConfirmationManagerShareMessagePlayer;
import traiwy.homePlugin.util.HomeManager;

import java.util.HashMap;
import java.util.UUID;

public class HomeAcceptCommand implements CommandExecutor {
    private final HashMap<UUID, ConfirmationManagerShareMessagePlayer.DoorSelection> transmissionSelection =
            ConfirmationManagerShareMessagePlayer.transmissionSelection;
    private final HomeManager homeManager;
    private final ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer;

    public HomeAcceptCommand(HomeManager homeManager, ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer) {
        this.homeManager = homeManager;
        this.confirmationManagerShareMessagePlayer = confirmationManagerShareMessagePlayer;
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

                       homeManager.setHome(player.getName(), "shared_" + senderPlayer.getName() + "_" + homeName, loc, senderPlayer.getName());
                        player.sendMessage("§aВы приняли доступ к дому '" + homeName + "' от " + senderPlayer.getName());
                        senderPlayer.sendMessage("§aИгрок " + player.getName() + " принял доступ к вашему дому '" + homeName + "'");
                        confirmationManagerShareMessagePlayer.confirm(senderUUID);
                        homeManager.setMemberPlayer(senderPlayer, homeName, player.getName());
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
