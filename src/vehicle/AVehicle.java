package vehicle;

import utility.ITimeManagment;
import utility.SpeedStruct;

import java.util.Objects;


/**
 * Абстрактный класс транспорта с реализацией интерфейса
 * @see ITimeManagment
 */
public abstract class AVehicle implements ITimeManagment
{
    /** Название транспорта */
    protected final String name;
    /** Тип транспорта */
    protected final EVehicleType type;
    /** Траектория движения */
    protected ITrack tracker;
    /** Скорость транспорта */
    protected SpeedStruct speed;

    /**
     * Конструктор класса
     * @param name Название транспорта
     * @param type Тип транспорта
     * @param speed Скорость транспорта
     * @param speed_type Единицы измерения скорости
     */
    protected AVehicle(String name, EVehicleType type, double speed, SpeedStruct.ESpeedType speed_type)
    {
        this.name = name;
        this.type = type;
        this.speed = new SpeedStruct(speed, speed_type);
    }

    /**
     * Получение названия транспорта
     * @return Название транспорта
     */
    public final String Name() { return this.name; }

    /**
     * Получение скорости
     * @return Объект скорости
     */
    public final SpeedStruct Speed() { return this.speed; }

    /**
     * Получение типа транспорта
     * @return Тип транспорта
     */
    public final EVehicleType Type() { return this.type; }

    /**
     * Задание скорости
     * @param speed Значение скорости
     * @param type Единицы измерения скорости
     */
    public final void Speed(double speed, SpeedStruct.ESpeedType type) { this.speed.Speed(speed, type); }

    /**
     * Задание скорости в текущих единицах измерения
     * @param speed Значение скорости
     */
    public final void Speed(double speed) { this.speed.Speed(speed); }

    /**
     * Изменение траектории движения
     * @param tracker Новая траектория
     */
    public final void changeTrack(ITrack tracker)
    {
        if (tracker != null)
        {
            this.tracker = tracker;
            System.out.printf("Выбрана новая траектория для %s - %s\n", this, this.tracker);

            if (this.speed.Speed() < this.tracker.totalDist()/5) // Выбор типа скорости относительно дистанции
                System.out.printf("Скорость %s невелика по сравнению с расстоянием. Приближения к %s визуально незаметно\n",
                        this.speed, this.tracker.end());
        }
    }

    /**
     * Движение по заданной траектории
     * @param hours Кол-во времени в часах
     */
    public final void move(int hours)
    {
        if (this.tracker == null)
        {
            System.out.printf("Траектория для %s не выбрана\n", this);
            return;
        }

        this.tracker.move(hours, this.speed.Speed());
        System.out.printf("Новое положение %s - %s\n", this, this.tracker.coord());
    }

    /**
     * @see ITimeManagment
     */
    @Override
    public void waitTime(int hours)
    {
        this.move(hours);
    }

    @Override
    public String toString()
    {
        return "Транспорт{name=" + this.name + ", type=" + this.type + ", speed=" + this.speed + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AVehicle vehicle= (AVehicle) o;
        return this.name.equals(vehicle.name) && this.type == vehicle.type
                && this.speed.equals(vehicle.speed) && this.tracker.equals(vehicle.tracker);
    }

    @Override
    public int hashCode() { return Objects.hash(this.name, this.type, this.speed, this.tracker); }

    /**
     * Перечисление видов транспорта
     */
    public enum EVehicleType
    {
        /**
         * Транспорт Ракета
         */
        ROCKET("Ракета");

        /**
         * Тип транспорта
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Тип транспорта
         */
        EVehicleType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }
}
