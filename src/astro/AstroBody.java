package astro;

import utility.CoordStruct;

import java.util.Objects;


/**
 * Класс космического объекта
 */
public final class AstroBody
{
    /** Имя объекта */
    private final String name;
    /** Тип объекта */
    private final EAstroType type;
    /** Координаты объекта */
    private final CoordStruct coord;

    /**
     * Конструктор класса
     * @param name Имя космического объекта
     * @param type Тип космического объекта
     * @param init_coord Начальные координаты космического объекта
     */
    public AstroBody(String name, EAstroType type, CoordStruct init_coord)
    {
        this.name = name;
        this.type = type;
        this.coord = init_coord;
    }

    /**
     * Получение имени космического объекта
     * @return Имя космического объекта
     */
    public String Name() { return this.name; }

    /**
     * Получение типа космического объекта
     * @return Тип космического объекта
     */
    public EAstroType Type() { return this.type; }

    /**
     * Получение координат космического объекта
     * @return Координаты космического объекта
     */
    public CoordStruct Coord() { return this.coord; }

    @Override
    public String toString() { return "КосмическоеТело{name=" + name + ", type=" + type + ", coord=" + this.coord + "}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroBody astroBody = (AstroBody) o;
        return this.name.equals(astroBody.name) && this.type == astroBody.type;
    }

    @Override
    public int hashCode() { return Objects.hash(this.name, this.type); }

    /**
     * Перечисление видов космических тел
     */
    public enum EAstroType
    {
        /**
         * Космическое тело Планета
         */
        PLANET("Планета"),
        /**
         * Космическое тело Спутник
         */
        SATELLITE("Спутник");

        /**
         * Имя космического тела
         */
        private final String name;

        /**
         * Конструктор перечислений
         * @param name Имя космического тела
         */
        EAstroType(String name) { this.name = name; }

        @Override
        public String toString() { return "Тип{name=" + this.name + "}"; }
    }
}
