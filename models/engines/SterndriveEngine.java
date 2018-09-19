package models.engines;

public class SterndriveEngine extends BaseEngine {
    private static final int STERNDRIVE_ENGINE_MULTIPLIER = 7;

    public SterndriveEngine(String model, int horsepower, int displacement) {
        super(model, horsepower, displacement);
    }

    @Override
    public int getOutput() {
        return super.getHorsepower() * STERNDRIVE_ENGINE_MULTIPLIER + getDisplacement();
    }
}
