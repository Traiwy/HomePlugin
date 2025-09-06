package util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConfirmationManagerDeleteHome {
    private static final Map<UUID, Long> confirmationTimes  = new HashMap<>();
     private static final long CONFIRMATION_TIMEOUT = 15000;

     public static boolean requiresConfirmation(UUID targetPlayer){
         cleanupExpired();
         return confirmationTimes.containsKey(targetPlayer) &&
                 (System.currentTimeMillis() - confirmationTimes.get(targetPlayer) <= CONFIRMATION_TIMEOUT);
     }
     public static void startConfirmation(UUID targetPlayer){
         confirmationTimes.put(targetPlayer, System.currentTimeMillis());
     }
     public static void cancelConfirmation(UUID targetPlayer){
         confirmationTimes.remove(targetPlayer);
     }
     public static void cleanupExpired(){
         long currentTime = System.currentTimeMillis();
          confirmationTimes.entrySet().removeIf(entry ->
            currentTime - entry.getValue() > CONFIRMATION_TIMEOUT);
     }
}
