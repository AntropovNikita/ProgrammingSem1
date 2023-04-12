package utility;

import java.util.Objects;


/**
 * Структура скорости движения. Выходное значение всегда в км/ч
 */
public final class SpeedStruct
{
    /** Значение скорости */
    private double speed;
    /** Единицы измерения скорости */
    private ESpeedType type;

    /**
     * Конструктор класса
     * @param speed Значение скорости
     * @param type Единицы измерения скорости
     */
    public SpeedStruct(double speed, ESpeedType type)
    {
        this.speed = speed;
        this.type = type;

        this.convert();
    }

    /**
     * Получение значения скорости
     * @return Значение скорости в км/ч
     */
    public double Speed() { return this.speed; }

    /**
     * Изменение скорости
     * @param speed Новое значение скорости
     * @param type Новые единицы измерения скорости
     */
    public void Speed(double speed, ESpeedType type) { this.speed = speed; this.type = type; convert(); }

    /**
     * Изменение скорости
     * @param speed Новое значение скорости
     */
    public void Speed(double speed) { this.Speed(speed, this.type); }

    /**
     * Конвертирования скорости в км/ч
     */
    private void convert()
    {
        switch (this.type)
        {
            case KM_PER_S: // Из км/с в км/ч
                this.speed *= 60*60;
                this.type = ESpeedType.KM_PER_H;
                break;
            case KM_PER_H:
            default:
                break;
        }
    }

    @Override
    public String toString() { return "Скорость{speed=" + this.speed + ", type=" + this.type + "}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpeedStruct speedStruct = (SpeedStruct) o;
        return Double.compare(this.speed, speedStruct.speed) == 0 && this.type == speedStruct.type;
    }

    @Override
    public int hashCode() { return Objects.hash(this.speed, this.type); }

    /**
     * Перечисление единиц измерения скорости
     */
    public enum ESpeedType
    {
        /**
         * Скорость в км/ч
         */
        KM_PER_H("км/ч"),
        /**
         * Скорост в км/с
         */
        KM_PER_S("км/с");

        /**
         * Название единиц измерения скорости
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Название единиц измерения скорости
         */
        ESpeedType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }
}
