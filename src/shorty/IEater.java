package shorty;

/**
 * Интерфейс поедателя
 */
public interface IEater
{
    /**
     * Поиск и поедание найденной пищи
     * @param amount Кол-во пищи
     */
    void eat(int amount);
}
