import astro.AstroBody;
import astro.AstroSpace;
import exceptions.NoDistanceException;
import exceptions.NoSuchPlaceInRocketException;
import shorty.ACreature;
import shorty.CreaturePlace;
import shorty.NeznaykaShorty;
import shorty.PonchikShorty;
import utility.*;
import vehicle.LineTrack;
import vehicle.Rocket;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws NoSuchPlaceInRocketException {
        AstroBody earth = new AstroBody("Земля", AstroBody.EAstroType.PLANET, new CoordStruct(0, 0, 0));
        AstroBody moon = new AstroBody("Луна", AstroBody.EAstroType.SATELLITE, new CoordStruct(282843, 282843, 0));

        AstroSpace space = new AstroSpace();
        space.addNewBody(earth);
        space.addNewBody(moon);
        space.distToFrom("Земля", "Луна");
        System.out.println();

        Rocket rocket = new Rocket("Коротышек", 12, SpeedStruct.ESpeedType.KM_PER_S);
        System.out.println();

        rocket.registerPlace("Комната с иллюминатором");

        try
        {
            APlaceStruct window_room = rocket.Place("Комната с иллюминатором");
            ThingStruct window = new ThingStruct("Иллюминатор", ThingStruct.EThingType.WINDOW, 1);
            window_room.addThing(window);
            System.out.println();
        }
        catch (NoSuchPlaceInRocketException e)
        {
            System.out.println(e.getMessage());
        }

        rocket.registerPlace("Пищевой отсек");

        try
        {
            APlaceStruct foodcort = rocket.Place("Пищевой отсек");
            ThingStruct food = new ThingStruct("Что-то", ThingStruct.EThingType.FOOD, 1000);
            foodcort.addThing(food);
            System.out.println();
        }
        catch (NoSuchPlaceInRocketException e)
        {
            System.out.println(e.getMessage());
        }

        NeznaykaShorty neznayka = new NeznaykaShorty(rocket.Place("Комната с иллюминатором"));
        PonchikShorty ponchik = new PonchikShorty(rocket.Place("Пищевой отсек"));

        CreaturePlace creaturePlace = new CreaturePlace();
        creaturePlace.registerCreature(neznayka, rocket.Place("Комната с иллюминатором"));
        creaturePlace.registerCreature(ponchik, rocket.Place("Пищевой отсек"));
        System.out.println();

        if (ponchik.whatFeel() == ACreature.EFeelsType.HUNGRY)
            ponchik.eat(1);
        System.out.println();

        neznayka.saw(space, "Луна");
        neznayka.watch();
        System.out.println();

        try
        {
            LineTrack track = new LineTrack(space.getBody("Земля"), space.getBody("Луна"));

            rocket.changeTrack(track);
            System.out.println();

            TimeManager manager = new TimeManager();
            manager.registerAgent(neznayka);
            manager.registerAgent(ponchik);
            manager.registerAgent(rocket);

            manager.waitTime(3);
            System.out.println();
        }
        catch (NoDistanceException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }

        if (neznayka.isHungry())
        {
            neznayka.feel(ACreature.EFeelsType.HUNGRY);
            neznayka.whatFeel();
            System.out.println();

            creaturePlace.registerCreature(neznayka, rocket.Place("Пищевой отсек"));
            ArrayList<ACreature> neighbors = creaturePlace.meetOther(neznayka);
            for(ACreature creature : neighbors)
            {
                System.out.printf("%s встретил %s\n", neznayka, creature);
                if (creature.equals(ponchik)) {
                    ponchik.eat(10);
                }
            }
        }
    }
}
