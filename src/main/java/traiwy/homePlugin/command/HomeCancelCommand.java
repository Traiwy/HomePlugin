package traiwy.homePlugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import traiwy.homePlugin.util.ConfirmationManagerShareMessagePlayer;

import java.util.HashMap;
import java.util.UUID;

public class HomeCancelCommand implements CommandExecutor {
    private final ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer;

    public HomeCancelCommand(ConfirmationManagerShareMessagePlayer confirmationManagerShareMessagePlayer) {
        this.confirmationManagerShareMessagePlayer = confirmationManagerShareMessagePlayer;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return false;
         for (HashMap.Entry<UUID, ConfirmationManagerShareMessagePlayer.DoorSelection> entry :
                ConfirmationManagerShareMessagePlayer.transmissionSelection.entrySet()) {

             ConfirmationManagerShareMessagePlayer.DoorSelection selection = entry.getValue();
             if (player.getName().equalsIgnoreCase(selection.getPlayerName())) {
                 UUID senderUUID = entry.getKey();
                 Player senderPlayer = Bukkit.getPlayer(senderUUID);

                 confirmationManagerShareMessagePlayer.cancelConfirmation(senderUUID);
                 player.sendMessage("Вы отменили запрос");
                 return true;
             }


         }
          return true;
    }
}
