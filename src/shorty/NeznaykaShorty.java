package shorty;

import astro.AstroBody;
import astro.AstroSpace;
import utility.APlaceStruct;
import utility.ThingStruct;

import java.util.Objects;


/**
 * Класс Незнайки наследуемый и реализующий интерфейс
 * @see ACreature
 * @see IAstroWatcher
 */
public final class NeznaykaShorty extends ACreature implements IAstroWatcher
{
    /** Наблюдаемое космическое тело */
    private AstroBody saw_body;

    /**
     * Конструктор класса
     * @param place Местоположение Незнайки
     */
    public NeznaykaShorty(APlaceStruct place)
    {
        super("Незнайка", ECreatureType.SHORTY, place, 2);
        this.saw_body = null;
    }

    /**
     * @see IAstroWatcher
     */
    @Override
    public void saw(AstroSpace space, String name)
    {
        this.saw_body = space.getBody(name);
        if (this.place.hasThing(ThingStruct.EThingType.WINDOW) && saw_body != null) // Если на месте есть иллюминатор
        {
            System.out.printf("%s увидел %s\n", this, this.saw_body);
            this.feel(EFeelsType.SUPRISED);
            this.whatFeel();
        }
        else
        {
            this.saw_body = null;
            System.out.printf("%s не видит %s\n", this, name);
            this.feel(EFeelsType.NEUTRAL);
        }
    }

    /**
     * @see IAstroWatcher
     */
    @Override
    public void watch()
    {
        if (this.saw_body != null)
            System.out.printf("%s наблюдает за %s\n", this, this.saw_body);
    }

    /**
     * @see IAstroWatcher
     */
    @Override
    public void stare()
    {
        if (this.saw_body != null)
        {
            System.out.printf("%s не может оторвать глаз от %s\n", this, this.saw_body);
            this.feel(EFeelsType.IMPRESSED);
            this.whatFeel();
        }
    }

    /**
     * @see utility.ITimeManagment
     */
    @Override
    public void waitTime(int hours)
    {
        super.waitTime(hours);
        this.stare();
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
        NeznaykaShorty neznaykaShorty = (NeznaykaShorty) o;
        return this.name.equals(neznaykaShorty.name) && this.type == neznaykaShorty.type;
    }
}
