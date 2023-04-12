package utility;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс управления пропусками времен
 */
public final class TimeManager
{
    /** Зарегистрированные агенты */
    private final ArrayList<ITimeManagment> agents;

    /**
     * Конструктор класса
     */
    public TimeManager() { this.agents = new ArrayList<>(); }

    /**
     * Регистрация агента
     * @param agent Объект агента
     */
    public void registerAgent(ITimeManagment agent)
    {
        if (agent != null && !this.agents.contains(agent)) // Если объект существует и не зарегистрирован
            this.agents.add(agent);
    }

    /**
     * Выполнение действий всеми агентами за отведенное время
     * @param hours Пропуск времени в часах
     */
    public void waitTime(int hours)
    {
        System.out.printf("Прошло %d часа(ов)\n", hours);
        for(ITimeManagment agent : this.agents)
            agent.waitTime(hours);
    }

    @Override
    public String toString() { return "МенеджерВремени{agents_size=" + this.agents.size() + "}"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeManager timeManager = (TimeManager) o;
        return this.agents.equals(timeManager.agents);
    }

    @Override
    public int hashCode() { return Objects.hash(this.agents); }
}
