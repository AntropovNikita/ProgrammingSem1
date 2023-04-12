package astro;

import java.util.HashSet;
import java.util.Set;


/**
 * Класс, содержащий информацию о космических телах
 */
public final class AstroSpace
{
    /**
     * Множество космических тел
     */
    private final Set<AstroBody> bodies;

    /**
     * Конструктор класса
     */
    public AstroSpace()
    {
        this.bodies = new HashSet<>();

        System.out.printf("Появилось %s\n", this);
    }

    /**
     * Добавление нового тела в космическое пространство
     * @param new_body Новое космическое тело
     */
    public void addNewBody(AstroBody new_body)
    {
        if (this.bodies.add(new_body))
            System.out.printf("%s появилось в %s\n", new_body, this);
    }

    /**
     * Получение космического тела, зарегистрированного в космическом пространстве
     * @param name Имя космического тела
     * @return Космическое тело, если найдено, иначе null
     */
    public AstroBody getBody(String name)
    {
        for(AstroBody body : this.bodies)
            if (body.Name().equals(name))
                return body;

        System.out.printf("Тело %s не найдено\n", name);
        return null;
    }

    /**
     * Вывод расстояния между космическими телами
     * @param to Начальное космическое тело
     * @param from Конечное космическое тело
     */
    public void distToFrom(String to, String from)
    {
        AstroBody body1 = null;
        AstroBody body2 = null;
        for(AstroBody body : this.bodies)
        {
            if (body.Name().equals(to))
                    body1 = body;
            else if (body.Name().equals(from))
                    body2 = body;
            if (body1 != null && body2 != null)
                break;
        }

        if (body1 == null)
        {
            System.out.printf("Тело %s не найдено в пространстве\n", to);
            return;
        }
        if (body2 == null)
        {
            System.out.printf("Тело %s не найдено в пространстве\n", from);
            return;
        }

        double dist = body1.Coord().distTo(body2.Coord());
        System.out.printf("Расстояние между %s и %s - %f км\n", body1, body2, dist);
    }

    @Override
    public String toString() { return "КосмическоеПространство{size=" + this.bodies.size() + "}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AstroSpace astros = (AstroSpace) o;
        return this.bodies.equals(astros.bodies);
    }

    @Override
    public int hashCode() { return this.bodies.hashCode(); }
}
