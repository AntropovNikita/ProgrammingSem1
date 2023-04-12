package exceptions;


/**
 * Unchecked исключение - отсутствие искомого места на ракете
 */
public class NoSuchPlaceInRocketException extends RuntimeException
{
    /**
     * Конструктор класса
     * @param message Сообщение об ошибке
     */
    public NoSuchPlaceInRocketException(String message)
    {
        super(message);
    }
}
