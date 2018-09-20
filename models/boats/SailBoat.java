package models.boats;

import utility.Validator;
import contracts.models.Race;

public class SailBoat extends BaseBoat {
    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.sailEfficiency = sailEfficiency;
    }

    public SailBoat(String model, int weight) {
        super(model, weight);
    }

    @Override
    public Double calculateRaceSpeed(Race race) {
        return (double)(race.getWindSpeed() * (getSailEfficiency() / 100) - getWeight() + race.getOceanCurrentSpeed());
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    public void setSailEfficiency(int oars) {
        Validator.ValidatePropertyValue(oars, "Oars");
        this.sailEfficiency = oars;
    }
}
