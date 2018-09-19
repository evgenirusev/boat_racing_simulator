package models.engines;

public class JetEngine extends BaseEngine {
    private static final int JET_ENGINE_MULTIPLIER = 5;

    public JetEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int getOutput() {
        return super.getHorsepower() * JET_ENGINE_MULTIPLIER + getDisplacement();
    }
}
