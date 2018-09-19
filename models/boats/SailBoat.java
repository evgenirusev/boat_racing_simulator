package models.boats;

import Utility.Validator;
import contracts.models.Race;

public class SailBoat extends AbstractBoat {
    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.sailEfficiency = sailEfficiency;
    }

    public SailBoat(String model, int weight) {
        super(model, weight);
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    public void setSailEfficiency(int oars) {
        Validator.ValidatePropertyValue(oars, "Oars");
        this.sailEfficiency = oars;
    }

    public double CalculateRaceSpeed(Race race) {

        return 0;
    }
}
