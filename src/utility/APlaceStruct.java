package utility;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Абстрактный класс места
 */
public abstract class APlaceStruct
{
    /** Название места */
    protected final String name;
    /** Вещи в локации */
    protected ArrayList<ThingStruct> things;

    /**
     * Конструктор класса
     * @param name Название места
     */
    protected APlaceStruct(String name)
    {
        this.name = name;
        this.things = new ArrayList<>();
    }

    /**
     * Получение названия места
     * @return Название места
     */
    public String Name() { return this.name; }

    /**
     * Проверка, есть ли вещь определенного типа в локации
     * @param type Тип вещи
     * @return true, если есть, иначе false
     */
    public boolean hasThing(ThingStruct.EThingType type)
    {
        for(ThingStruct thing : this.things)
            if (thing.Type() == type)
                return true;
        return false;
    }

    /**
     * Получение вещей в локации
     * @return Список вещей в локации
     */
    public ArrayList<ThingStruct> getThings() { return this.things; }

    /**
     * Уменьшение кол-ва вещи в локации
     * @param thing Объект вещи
     * @return true, если получилось уменьшить, иначе false
     */
    public boolean removeThing(ThingStruct thing)
    {
        for(ThingStruct thing1 : this.things)
            if (thing.equals(thing1) && thing1.Amount() >= thing.Amount()) // Если вещи одинаковые, и текущее кол-во меньше желаемого для удаления
            {
                System.out.printf("Уменьшилось кол-во %s в %s на %d\n", thing1, this, thing.Amount());
                thing1.addAmount(-thing.Amount());
                if (thing1.Amount() <= 0) // Если вещей больше не осталось, удаляем с локации
                {
                    System.out.printf("Закончилась %s в %s\n", thing1, this);
                    this.things.remove(thing1);
                }
                return true;
            }

        return false;
    }

    /**
     * Добавление вещи в локацию
     * @param thing Объект вещи
     */
    public void addThing(ThingStruct thing)
    {
        for(ThingStruct thing1 : this.things)
            if (thing.equals(thing1))
            {
                System.out.printf("Увеличено кол-во %s в %s на %d\n", thing1, this, thing.Amount());
                thing1.addAmount(thing.Amount());
                return;
            }

        System.out.printf("Появилась %s в %s\n", thing, this);
        this.things.add(thing);
    }

    @Override
    public String toString()
    {
        return "Помещение{name=" + this.name + ", things_size=" + this.things.size() + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        APlaceStruct aPlaceStruct = (APlaceStruct) o;
        return this.name.equals(aPlaceStruct.name);
    }

    @Override
    public int hashCode() { return Objects.hash(this.name); }
}
