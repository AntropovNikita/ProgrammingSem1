package utility;

import java.util.Objects;


/**
 * Структура координат тел в пространстве в км
 */
public final class CoordStruct
{
    /** Координата по оси X в км */
    private double X;
    /** Координата по оси Y в км */
    private double Y;
    /** Координата по оси Z в км */
    private double Z;

    /**
     * Конструктор класса
     * @param X Координата по оси X в км
     * @param Y Координата по оси Y в км
     * @param Z Координата по оси Z в км
     */
    public CoordStruct(double X, double Y, double Z)
    {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    /**
     * Получение координаты по оси X
     * @return Координата по оси X в км
     */
    public double X() { return this.X; }

    /**
     * Получение координаты по оси Y
     * @return Координата по оси Y в км
     */
    public double Y() { return this.Y; }

    /**
     * Получение координаты по оси Z
     * @return Координата по оси Z в км
     */
    public double Z() { return this.Z; }

    /**
     * Изменение координат
     * @param dCoord Сдвиг по координатам в км
     */
    public void changeCoord(CoordStruct dCoord) { this.X += dCoord.X; this.Y += dCoord.Y; this.Z += dCoord.Z; }

    /**
     * Разница по координатам между двумя точками
     * @param other Координаты другой точки в км
     * @return Разница по координатам в км
     */
    public CoordStruct diffCoord(CoordStruct other)
    {
        return new CoordStruct(other.X - this.X, other.Y - this.Y, other.Z - this.Z);
    }

    /**
     * Расстояние до другой точки в км
     * @param other Другая точка
     * @return Расстояние в км
     */
    public double distTo(CoordStruct other)
    {
        return Math.sqrt(Math.pow((this.X - other.X), 2) + Math.pow((this.Y - other.Y), 2) +
                Math.pow((this.Z - other.Z), 2));
    }

    @Override
    public String toString() { return "Координаты{X=" + X + ", Y=" + Y + ", Z=" + Z + ",тип=км}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordStruct that = (CoordStruct) o;
        return Double.compare(that.X, this.X) == 0 && Double.compare(that.Y, this.Y) == 0 &&
                Double.compare(that.Z, this.Z) == 0;
    }

    @Override
    public int hashCode() { return Objects.hash(this.X, this.Y, this.Z); }
}
