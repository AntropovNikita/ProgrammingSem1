package vehicle;

import astro.AstroBody;
import exceptions.NoDistanceException;
import utility.CoordStruct;

import java.util.Objects;


/**
 * Передвижение по прямолинейной траектории между космическими телами
 * @see ITrack
 */
public final class LineTrack implements ITrack
{
    /** Стартовое космическое тело */
    AstroBody start;
    /** Конечное космическое тело */
    AstroBody end;
    /** Текущие координаты */
    CoordStruct coord;

    /**
     * Конструктор класса
     * @param start Стартовое космическое тело
     * @param end Конечное космическое тело
     */
    public LineTrack(AstroBody start, AstroBody end) throws NoDistanceException, NullPointerException
    {
        if (start == null || end == null)
            throw new NullPointerException("Заданные космические тела не найдены");
        if (start.Coord().equals(end.Coord()))
            throw new NoDistanceException("Нулевая дистанция между " + start + " и " + end);

        this.start = start;
        this.end = end;

        this.coord = new CoordStruct(this.start.Coord().X(), this.start.Coord().Y(), this.start.Coord().Z());
    }

    /**
     * @see ITrack
     */
    @Override
    public void move(int hours, double speed)
    {
        double distance = this.coord.distTo(end.Coord());
        if (distance > 0) // Если еще не долетели
        {
            CoordStruct dCoord = this.coord.diffCoord(end.Coord());

            double dZ = speed*hours*dCoord.Z()/distance; // Проекция на ось Z
            double dXY = speed*hours*Math.sqrt(Math.pow(dCoord.X(), 2) + Math.pow(dCoord.Y(), 2))/distance; // Проекция на плоскость XY

            double dY = dXY*dCoord.Y()/Math.sqrt(Math.pow(dCoord.X(), 2) + Math.pow(dCoord.Y(), 2)); // Проекция на ось Y
            double dX = dXY*dCoord.X()/Math.sqrt(Math.pow(dCoord.X(), 2) + Math.pow(dCoord.Y(), 2)); // Проекция на ось X

            this.coord.changeCoord(new CoordStruct(dX, dY, dZ)); // Сдвиг на заданные перемещения

            if (this.start.Coord().distTo(end.Coord()) < this.start.Coord().distTo(this.coord)) // Если перелетели
                this.coord = this.end.Coord();
        }
    }

    /**
     * @see ITrack
     */
    @Override
    public double totalDist()
    {
        return this.start.Coord().distTo(this.end.Coord());
    }

    /**
     * @see ITrack
     */
    @Override
    public String start() { return this.start.toString(); }

    /**
     * @see ITrack
     */
    @Override
    public String end() { return this.end.toString(); }

    /**
     * @see ITrack
     */
    @Override
    public String coord() { return this.coord.toString(); }

    @Override
    public String toString()
    {
        return "ПрямолинейноеДвижение{start=" + this.start + ", end=" + this.end + ", coord=" + this.coord + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineTrack lineTrack = (LineTrack) o;
        return start.equals(lineTrack.start) && end.equals(lineTrack.end) && coord.equals(lineTrack.coord);
    }

    @Override
    public int hashCode() { return Objects.hash(start, end, coord); }
}
