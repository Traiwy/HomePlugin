package traiwy.homePlugin.error.provider;

import traiwy.homePlugin.error.CommandError;
import traiwy.homePlugin.error.RequestError;

public class RequestErrorMessageProvider {
    public static String getMessage(RequestError error) {
        return switch (error) {
            case REQUEST_ALREADY_SENT ->
                    "Вы уже отправили этому игроку заявку.";

            case REQUEST_ALREADY_EXISTS ->
                    "У игрока уже есть активная заявка.";

            case REQUEST_NOT_FOUND ->
                    "Активная заявка не найдена.";

            case REQUEST_EXPIRED ->
                    "Время заявки истекло.";

            case REQUEST_CANCELLED ->
                    "Заявка была отменена.";

            case REQUEST_COOLDOWN ->
                    "Подождите немного перед отправкой новой заявки.";

            case REQUEST_BLOCKED ->
                    "Этот игрок запретил принимать заявки.";

            case TOO_MANY_REQUESTS ->
                    "У вас слишком много активных заявок.";
        };
    }
}