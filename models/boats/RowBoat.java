package models.boats;

import utility.Validator;
import contracts.models.Race;

public class RowBoat extends BaseBoat {
    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.oars = oars;
    }

    public int getOars() {
        return oars;
    }

    public void setOars(int oars) {
        Validator.ValidatePropertyValue(oars, "Oars");
        this.oars = oars;
    }

    public double CalculateRaceSpeed(Race race) {

        return 0;
    }
}


