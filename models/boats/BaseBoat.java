package models.boats;

import contracts.models.Race;
import utility.Validator;
import contracts.models.Boat;

public abstract class BaseBoat implements Boat {
    private String model;
    private int weight;

    public BaseBoat(String model, int weight) {
        this.model = model;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        Validator.ValidatePropertyValue(weight, "Weight");
        this.weight = weight;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public int getWeight() {
        return weight;
    }

    public abstract Double calculateRaceSpeed(Race race);
}
