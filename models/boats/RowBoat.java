package models.boats;

import Utility.Constants;
import Utility.Validator;
import contracts.Modelable;
import contracts.models.Race;

import java.util.List;

public class RowBoat extends AbstractBoat {
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


