package vehicle;

import exceptions.NoSuchPlaceInRocketException;
import utility.APlaceStruct;
import utility.SpeedStruct;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс ракеты, наследуемый
 * @see AVehicle
 */
public final class Rocket extends AVehicle
{
    /** Зарегистрированные места на ракете */
    private final ArrayList<RocketPlace> places;

    /**
     * Конструктор класса
     * @param name Название ракеты
     * @param speed Скорость ракеты
     * @param speed_type Единицы измерения скорости
     */
    public Rocket(String name, double speed, SpeedStruct.ESpeedType speed_type)
    {
        super(name, EVehicleType.ROCKET, speed, speed_type);

        this.places = new ArrayList<>();

        System.out.printf("Появился %s\n", this);
    }

    /**
     * Регистрация места в ракете
     * @param name Название места
     */
    public void registerPlace(String name)
    {
        RocketPlace place =new RocketPlace(name);
        if (this.places.contains(place))
            System.out.printf("Помещение %s уже существует в %s\n", name, this);
        else
        {
            this.places.add(place);
            System.out.printf("Зарегистрировано новое помещение %s в %s\n", name, this);
        }
    }

    /**
     * Получение места по названию
     * @param name Название места
     * @return Объект места
     */
    public RocketPlace Place(String name)
    {
        for(RocketPlace place : this.places)
            if (place.Name().equals(name))
                return place;

        throw new NoSuchPlaceInRocketException("В " + this + " нет места " + name);
    }

    /**
     * Класс места в ракете наследуемый
     * @see APlaceStruct
     */
    public static class RocketPlace extends APlaceStruct
    {
        /**
         * Конструктор класса
         * @param name Название места
         */
        public RocketPlace(String name) { super(name); }

        @Override
        public String toString()
        {
            return "ПомещениеВРакете{name=" + this.name + ", things_size=" + this.things.size() + "}";
        }
    }

    @Override
    public String toString()
    {
        return "Ракета{name=" + this.name + ", speed=" + this.speed + ", places_size=" + this.places.size() + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return this.name.equals(rocket.name) && this.speed.equals(rocket.speed) && this.type == rocket.type &&
                this.tracker.equals(rocket.tracker) && this.places.equals(rocket.places);
    }

    @Override
    public int hashCode() { return Objects.hash(this.name, this.type, this.speed, this.tracker, this.places); }
}
