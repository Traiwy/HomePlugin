package util;

import java.util.HashMap;
import java.util.UUID;

public class ConfirmationManagerShareMessagePlayer {
    private static final HashMap<UUID, DoorSelection> transmissionSelection = new HashMap<>();
    private static final HashMap<UUID, Long> confirmationTimes = new HashMap<>();
    private static final long CONFIRMATION_TIMEOUT = 60000L;

    public static class DoorSelection {
        private final String playerName;
        private final String doorName;

        public DoorSelection(String playerName, String doorName) {
            this.playerName = playerName;
            this.doorName = doorName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getDoorName() {
            return doorName;
        }

        @Override
        public String toString() {
            return playerName + " выбрал дверь: " + doorName;
        }
    }

    public boolean startWaitingConfirmation(UUID playerID) {
        cleanupExpired();
        return transmissionSelection.containsKey(playerID) &&
                confirmationTimes.containsKey(playerID) &&
                (System.currentTimeMillis() -
                        confirmationTimes.get(playerID)) <= CONFIRMATION_TIMEOUT;
    }

    public void startSelection(UUID playerID, String playerName, String doorName) {
        cleanupExpired();
        transmissionSelection.put(playerID, new DoorSelection(playerName, doorName));
        confirmationTimes.put(playerID, System.currentTimeMillis());
    }

    public void cleanupExpired() {
        long currentTime = System.currentTimeMillis();
        transmissionSelection.entrySet().removeIf(entry ->
                !confirmationTimes.containsKey(entry.getKey()) ||
                        currentTime - confirmationTimes.get(entry.getKey()) > CONFIRMATION_TIMEOUT);
        confirmationTimes.entrySet().removeIf(entry ->
                currentTime - entry.getValue() > CONFIRMATION_TIMEOUT);
    }

    public DoorSelection getConfirmedShare(UUID playerID) {
        return transmissionSelection.get(playerID);
    }

    public void confirm(UUID targetPlayer) {
        transmissionSelection.remove(targetPlayer);
        confirmationTimes.remove(targetPlayer);
    }

    public void cancelConfirmation(UUID targetPlayer) {
        transmissionSelection.remove(targetPlayer);
        confirmationTimes.remove(targetPlayer);
    }
}