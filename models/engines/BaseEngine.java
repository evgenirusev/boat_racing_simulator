package models.engines;

import contracts.models.BoatEngine;

public abstract class BaseEngine implements BoatEngine {
    private String model;
    private int output;
    private int displacement;
    private int horsepower;

    public BaseEngine(String model, int horsepower, int displacement) {
        this.model = model;
        this.displacement = displacement;
        this.horsepower = horsepower;
    }

    public int getDisplacement() {
        return displacement;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public abstract int getOutput();
}
