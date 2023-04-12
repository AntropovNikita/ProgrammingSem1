package utility;


/**
 * Интерфейс управления действиями при пропуске временем
 */
public interface ITimeManagment
{
    /**
     * Действие при пропуске времени
     * @param hours Прошедшее время в часах
     */
    void waitTime(int hours);
}
