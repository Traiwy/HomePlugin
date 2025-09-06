package util;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ConfirmationManagerShareHome {
    private static final HashMap<UUID, String> transmissionSelection = new HashMap<>();
    private static final HashMap<UUID, Long> confirmationTimes = new HashMap<>();
    private static final long CONFIRMATION_TIMEOUT = 15000L;
    public void startConfirmation(UUID targetPlayer, String nameHome) {
        transmissionSelection.put(targetPlayer, nameHome);
        confirmationTimes.put(targetPlayer, System.currentTimeMillis());
    }

    public boolean requiresConfirmation(UUID playerId) {
        cleanupExpired();
        return transmissionSelection.containsKey(playerId) &&
               confirmationTimes.containsKey(playerId) &&
               (System.currentTimeMillis() -
                       confirmationTimes.get(playerId)) <= CONFIRMATION_TIMEOUT;
    }

    public String getConfirmedHomeName(UUID playerId) {
        return transmissionSelection.get(playerId);
    }

    public void confirm(UUID targetPlayer) {
        transmissionSelection.remove(targetPlayer);
        confirmationTimes.remove(targetPlayer);
    }

    public void cancelConfirmation(UUID targetPlayer) {
        transmissionSelection.remove(targetPlayer);
        confirmationTimes.remove(targetPlayer);
    }

    private void cleanupExpired() {
        long currentTime = System.currentTimeMillis();
        transmissionSelection.entrySet().removeIf(entry ->
            !confirmationTimes.containsKey(entry.getKey()) ||
            currentTime - confirmationTimes.get(entry.getKey()) > CONFIRMATION_TIMEOUT
        );
        confirmationTimes.entrySet().removeIf(entry ->
            currentTime - entry.getValue() > CONFIRMATION_TIMEOUT
        );
    }
}
