package factories;

import contracts.models.BoatEngine;
import core.Engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class EngineFactory {
    private static final String ENGINE_PATH = "models.engines.";

    private EngineFactory() { }

    public static BoatEngine create(String engineType) {
        BoatEngine engine = null;
        try {
            Class<?> attackClass = Class.forName(ENGINE_PATH + engineType);
            Constructor<?> declaredConstructor = attackClass.getDeclaredConstructor();
            return (BoatEngine) declaredConstructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            return engine;
        }
    }
}
