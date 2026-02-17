package traiwy.homePlugin.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import net.kyori.adventure.text.Component;

@Getter
@Setter
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessageConfiguration {
    private String console = "Команда доступна только игрокам.";
    private String noPermission = "У вас нет прав на использование этой команды.";
    private String args = "Неверное использование команды.";
    private String unknowSubcommand = "Неизвестная подкоманда.";
    private String notFindPlayer = "Игрок не найден.";
    private String targetOffline = "Игрок сейчас не в сети.";
    private String playerNotOnline = "Игрок не в сети.";
    private String selfTarget = "Нельзя выполнить это действие с самим собой.";

    private String homeNotFound = "Дом не найден.";
    private String homeAlreadyShared = "Этот дом уже является общим.";
    private String alreadyInHome = "Игрок уже состоит в этом доме.";
    private String notHomeOwner = "Только владелец дома может выполнить это действие.";
    private String homeLimitReached = "Достигнут лимит участников дома.";
    private String internalError = "Произошла внутренняя ошибка. Обратитесь к администратору.";

    private String requestAlreadySent = "Вы уже отправили этому игроку заявку.";
    private String requestAlreadyExists = "У игрока уже есть активная заявка.";
    private String requestNotFound = "Активная заявка не найдена.";
    private String requestExpired = "Время заявки истекло.";
    private String requestCancelled = "Заявка была отменена.";
    private String requestCooldown = "Подождите немного перед отправкой новой заявки.";
    private String requestBlocked = "Этот игрок запретил принимать заявки.";
    private String tooManyRequests = "У вас слишком много активных заявок.";
    private String memberAlreadyExists = "Вы уже находитесь в данной точке дома";


}
