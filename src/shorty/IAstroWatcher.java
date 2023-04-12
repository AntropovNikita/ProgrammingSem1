package shorty;

import astro.AstroSpace;


/**
 * Интерфейс наблюдателя за космическими объектами
 */
public interface IAstroWatcher
{
    /**
     * Поиск необходимого космического объекта
     * @param space Космическое пространство
     * @param name Название космического объекта
     */
    void saw(AstroSpace space, String name);

    /**
     * Слежение за космическим объектом
     */
    void watch();

    /**
     *  Рассматривание космического объекта
     */
    void stare();
}
