package shorty;

import utility.APlaceStruct;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс регистрации существ с местоположением
 */
public final class CreaturePlace
{
    /** Зарегистрированные существа */
    private final ArrayList<ACreature> creatures;

    /**
     * Конструктор класса
     */
    public CreaturePlace() { this.creatures = new ArrayList<>(); }

    /**
     * Регистрация или смена местоположения
     * @param creature Объект существа
     * @param place Новое место
     */
    public void registerCreature(ACreature creature, APlaceStruct place)
    {
        if (place != null) // Место существует
        {
            if (this.creatures.contains(creature))
            {
                System.out.printf("%s сменил местоположение с %s на %s\n", creature, creature.Place(), place);
                creature.Place(place);
            }
            else
            {
                System.out.printf("%s появился в %s\n", creature, place);
                creature.Place(place);
                this.creatures.add(creature);
            }
        }
    }

    /**
     * Получение местоположения зарегистрированного существа
     * @param creature Объект существа
     * @return Объект места, если существо зарегистрировано, иначе null
     */
    private APlaceStruct creaturePlace(ACreature creature)
    {
        for(ACreature creature1 : this.creatures)
            if (creature.equals(creature1))
                return creature.Place();
        return null;
    }

    /**
     * Поиск тех, кто находится в том же месте
     * @param creature Объект существа
     * @return Массив существ в том же месте
     */
    public ArrayList<ACreature> meetOther(ACreature creature)
    {
        ArrayList<ACreature> neighbors = new ArrayList<>();
        APlaceStruct place = this.creaturePlace(creature);
        if (place != null) // Если место есть
        {
            for (ACreature creature1 : this.creatures)
                if (!creature.equals(creature1) && place.equals(creature1.Place()))
                    neighbors.add(creature1);
        }

        return neighbors;
    }

    @Override
    public String toString() { return "РегистраторСуществ{creatures_size=" + this.creatures + "}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreaturePlace creature = (CreaturePlace) o;
        return this.creatures.equals(creature.creatures);
    }

    @Override
    public int hashCode() { return Objects.hash(this.creatures); }
}
