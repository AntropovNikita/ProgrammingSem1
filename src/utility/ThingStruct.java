package utility;

import java.util.Objects;


/**
 * Структура вещи
 */
public final class ThingStruct
{
    /** Название вещи */
    private final String name;
    /** Тип вещи */
    private final EThingType type;
    /** Кол-во вещи*/
    private int amount;

    /**
     * Конструктор класса
     * @param name Название вещи
     * @param type Тип вещи
     * @param amount Кол-во вещи
     */
    public ThingStruct(String name, EThingType type, int amount)
    {
        this.name = name;
        this.type = type;
        if (amount > 0) this.amount = amount; // Если кол-во меньше 0, то 0
        else this.amount = 0;
    }

    /**
     * Получение названия вещи
     * @return Название вещи
     */
    public String Name() { return this.name; }

    /**
     * Получение типа вещи
     * @return Тип вещи
     */
    public EThingType Type() { return this.type; }

    /**
     * Получение кол-ва вещи
     * @return Кол-во вещи
     */
    public int Amount() { return this.amount; }

    /**
     * Изменение кол-ва вещи
     * @param dAmount Изменение кол-ва вещи
     */
    public void addAmount(int dAmount)
    {
        this.amount += dAmount;
        if (this.amount < 0) this.amount = 0; // Если кол-во меньше 0, то 0
    }

    /**
     * Проверка наличия вещи
     * @return true, если кол-во больше 0, иначе false
     */
    public boolean isExisting() { return this.amount > 0; }

    @Override
    public String toString()
    {
        return "Вещь{name=" + this.name + ", type=" + this.type + ", amount="  + this.amount + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThingStruct thingStruct = (ThingStruct) o;
        return this.name.equals(thingStruct.name) && this.type == thingStruct.type;
    }

    @Override
    public int hashCode() { return Objects.hash(this.name, this.type); }

    /**
     * Перечисление типов вещей
     */
    public enum EThingType
    {
        /**
         * Тип вещи Еда
         */
        FOOD("Еда"),
        WINDOW("Иллюминатор");

        /**
         * Название типа вещи
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Название типа вещи
         */
        EThingType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }
}
