package models.boats;

import Utility.Constants;
import Utility.Validator;

public abstract class AbstractBoat {
    private String model;
    private int weight;

    public AbstractBoat(String model, int weight) {
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
}
