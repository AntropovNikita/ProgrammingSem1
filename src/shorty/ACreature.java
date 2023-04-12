package shorty;

import utility.APlaceStruct;
import utility.ITimeManagment;

import java.util.Objects;


/**
 * Абстрактный класс существа, реализующий интерфейс
 * @see ITimeManagment
 */
public abstract class ACreature implements ITimeManagment
{
    /** Имя существа */
    protected final String name;
    /** Тип существа */
    protected final ECreatureType type;
    /** Состояние существа */
    protected EFeelsType feel;
    /** Положение существа */
    protected APlaceStruct place;
    /** Уровень голода существа */
    protected int hungryStamina;

    /**
     * Конструктор класса
     * @param name Имя существа
     * @param type Тип существа
     * @param place Местоположение существа
     * @param hungryStamina Уровень голода существа
     */
    protected ACreature(String name, ECreatureType type, APlaceStruct place, int hungryStamina)
    {
        this.name = name;
        this.type = type;
        this.place = place;
        this.hungryStamina = hungryStamina;

        if (this.hungryStamina < 0) // Если уровень голода ниже 0, состояние - голодный
            this.feel = EFeelsType.HUNGRY;
        else
            this.feel = EFeelsType.NEUTRAL;
    }

    /**
     * Получение имени существа
     * @return Имя существа
     */
    public final String Name() { return this.name; }

    /**
     * Получение типа существа
     * @return Тип существа
     */
    public final ECreatureType Type() { return this.type; }

    /**
     * Получение местоположения существа
     * @return Местоположение существа
     */
    public final APlaceStruct Place() { return this.place; }

    /**
     * Получение состояния существа
     * @return Состояние существа
     */
    public final EFeelsType whatFeel()
    {
        System.out.printf("%s испытывает %s\n", this, this.feel);
        return this.feel;
    }

    /**
     * Проверка на голод
     * @return true, если голодный, иначе false
     */
    public boolean isHungry() { return hungryStamina < 0; }

    /**
     * Выбор состояния существа
     * @param feel Новое состояния существа
     */
    public final void feel(EFeelsType feel) { this.feel = feel;  }

    /**
     * Выбор местоположения существа
     * @param place Новое местоположение существа
     */
    public final void Place(APlaceStruct place) { if (place != null) this.place = place; }

    /**
     * @see ITimeManagment
     */
    @Override
    public void waitTime(int hours) { this.hungryStamina -= hours; }

    @Override
    public String toString()
    {
        return "Создание{name=" + this.name + ", type=" + this.type + ", hungryStamina=" + this.hungryStamina +
                ", feel=" + this.feel + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ACreature creature = (ACreature) o;
        return this.name.equals(creature.name) && this.type == creature.type;
    }

    @Override
    public int hashCode() { return Objects.hash(this.name, this.type); }

    /**
     * Перечисление видов созданий
     */
    public enum ECreatureType
    {
        /**
         * Создание вида Коротышка
         */
        SHORTY("Коротышка");

        /**
         * Вид существа
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Вид существа
         */
        ECreatureType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }

    /**
     * Перечисление состояний существа
     */
    public enum EFeelsType
    {
        /** Состояние Спокойный */
        NEUTRAL("Cпокойствие"),
        /** Состояние Голодный */
        HUNGRY("Голод"),
        /** Состояние Удивлен */
        SUPRISED("Удивление"),
        /** Состояние Впечатлен */
        IMPRESSED("Впечатление");

        /**
         * Название состояния
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Название состояния
         */
        EFeelsType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }
}
