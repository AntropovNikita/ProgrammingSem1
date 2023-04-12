package shorty;

import utility.APlaceStruct;
import utility.ThingStruct;

import java.util.Objects;


/**
 * Класс Пончика, наследующий и реализующий интерфейс
 * @see ACreature
 * @see IEater
 */
public final class PonchikShorty extends ACreature implements IEater
{
    /**
     * Конструктор класса
     * @param place Местоположение Пончика
     */
    public PonchikShorty(APlaceStruct place)
    {
        super("Пончик", ECreatureType.SHORTY, place, -100);
    }

    /**
     * @see IEater
     */
    @Override
    public void eat(int amount)
    {
        if (this.place != null && this.feel == EFeelsType.HUNGRY)
        {
            int remain;
            for (ThingStruct thing : this.place.getThings())
                if (thing.Type() == ThingStruct.EThingType.FOOD)
                {
                    System.out.printf("%s обнаружил %s\n", this, thing);

                    remain = amount - thing.Amount();

                    if (remain > 0)
                    {
                        System.out.printf("%s ест %s в кол-ве %d\n", this, thing, amount - remain);
                        this.hungryStamina += amount - remain;

                    }
                    else
                    {
                        System.out.printf("%s ест %s в кол-ве %d\n", this, thing,  amount);
                        this.hungryStamina += amount;
                    }

                    place.removeThing(new ThingStruct(thing.Name(), thing.Type(), amount));
                    amount = Math.abs(remain);

                    if (amount == 0)
                        break;
                }
        }
    }

    /**
     * @see utility.ITimeManagment
     */
    @Override
    public void waitTime(int hours)
    {
        super.waitTime(hours);
        this.eat(hours*2);
    }

    @Override
    public String toString()
    {
        return "Коротышка{name=" + this.name + ", hungryStamina=" + this.hungryStamina + ", feel=" + this.feel + "}";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PonchikShorty ponchikShorty = (PonchikShorty) o;
        return this.name.equals(ponchikShorty.name) && this.type == ponchikShorty.type;
    }
}
