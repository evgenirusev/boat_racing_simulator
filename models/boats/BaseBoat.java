package models.boats;

import Utility.Constants;
import Utility.Validator;

public abstract class BaseBoat {
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
}
