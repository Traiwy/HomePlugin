package traiwy.homePlugin.error.provider;

import traiwy.homePlugin.error.CommandError;

public class CommandErrorMessageProvider {

    public static String getMessage(CommandError error) {
        return switch (error) {

            case CONSOLE ->
                    "Команда доступна только игрокам.";

            case NO_PERMISSION ->
                    "У вас нет прав на использование этой команды.";

            case ARGS ->
                    "Неверное использование команды.";

            case UNKNOWN_SUBCOMMAND ->
                    "Неизвестная подкоманда.";

            case NOT_FIND_PLAYER ->
                    "Игрок не найден.";

            case TARGET_OFFLINE ->
                    "Игрок сейчас не в сети.";

            case PLAYER_NOT_ONLINE ->
                    "Игрок не в сети.";

            case SELF_TARGET ->
                    "Нельзя выполнить это действие с самим собой.";
        };
    }
}
