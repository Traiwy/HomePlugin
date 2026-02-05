package traiwy.homePlugin.error.provider;

import traiwy.homePlugin.error.HomeError;

public class HomeErrorMessageProvider {

    public static String getMessage(HomeError error) {
        return switch (error) {

            case HOME_NOT_FOUND ->
                    "Дом не найден.";

            case HOME_ALREADY_SHARED ->
                    "Этот дом уже является общим.";

            case ALREADY_IN_HOME ->
                    "Игрок уже состоит в этом доме.";

            case NOT_HOME_OWNER ->
                    "Только владелец дома может выполнить это действие.";

            case HOME_LIMIT_REACHED ->
                    "Достигнут лимит участников дома.";

            case INTERNAL_ERROR ->
                    "Произошла внутренняя ошибка. Обратитесь к администратору.";
        };
    }
}
