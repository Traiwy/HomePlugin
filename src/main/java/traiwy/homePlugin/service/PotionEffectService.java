package traiwy.homePlugin.service;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionEffectService {
    public void giveEffect(Player player, String effectName, int duration, int amplifier) {

        final PotionEffectType type = PotionEffectType.getByName(effectName.toUpperCase());

        if (type == null) {
            player.sendMessage("§cUnknown effect: " + effectName);
            return;
        }
        player.removePotionEffect(type);
        PotionEffect effect = new PotionEffect(
                type,
                duration * 20,
                amplifier,
                false,
                true,
                true
        );

        player.addPotionEffect(effect);
    }

    public void removeEffect(Player player, String effectName) {
        PotionEffectType type = PotionEffectType.getByName(effectName.toUpperCase());
        if (type != null) {
            player.removePotionEffect(type);
        }
    }

    public void clearAll(Player player) {
        player.getActivePotionEffects()
                .forEach(e -> player.removePotionEffect(e.getType()));
    }


}
