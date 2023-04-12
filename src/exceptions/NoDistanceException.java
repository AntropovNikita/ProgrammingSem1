package exceptions;


/**
 * Cheked исключение - Нулевая дистанция на траектории
 */
public class NoDistanceException extends Exception
{
    /**
     * Конструктор класса
     * @param message Сообщение об ошибке
     */
    public NoDistanceException(String message)
    {
        super(message);
    }
}
