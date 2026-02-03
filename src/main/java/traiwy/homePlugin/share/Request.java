package traiwy.homePlugin.share;

import org.bukkit.entity.Player;


public record Request(Player sender, Player receiver, int time) {
    public String getFormattedTime() {
        int minutes = time / 60;
        int seconds = time % 60;

        StringBuilder sb = new StringBuilder();

        if (minutes > 0) {
            sb.append(minutes)
                    .append(" ")
                    .append(getMinutesWord(minutes));
        }

        if (seconds > 0) {
            if (!sb.isEmpty()) sb.append(" ");
            sb.append(seconds)
                    .append(" ")
                    .append(getSecondsWord(seconds));
        }

        return sb.isEmpty() ? "0 сек" : sb.toString();
    }

    private String getMinutesWord(int value) {
        if (value % 10 == 1 && value % 100 != 11) return "минута";
        if (value % 10 >= 2 && value % 10 <= 4 &&
                (value % 100 < 10 || value % 100 >= 20)) return "минуты";
        return "минут";
    }

    private String getSecondsWord(int value) {
        if (value % 10 == 1 && value % 100 != 11) return "секунда";
        if (value % 10 >= 2 && value % 10 <= 4 &&
                (value % 100 < 10 || value % 100 >= 20)) return "секунды";
        return "секунд";
    }
}
