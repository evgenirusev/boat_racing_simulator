package factories;

import contracts.models.BoatEngine;
import core.Engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class EngineFactory {
    private static final String ENGINE_PATH = "models.engines.";
    private static final String ENGINE_SUFFIX = "Engine";

    private EngineFactory() { }

    public static BoatEngine create(String engineType, String model, int horsepower, int displacement) {
        BoatEngine engine = null;
        try {
            Class<?> attackClass = Class.forName(ENGINE_PATH + engineType + ENGINE_SUFFIX);
            Constructor<?> declaredConstructor = attackClass.getDeclaredConstructor(String.class, int.class, int.class);
            return (BoatEngine) declaredConstructor.newInstance(model, horsepower, displacement);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return engine;
        }
    }
}
