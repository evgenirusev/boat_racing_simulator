package models.boats;

import models.JetEngine;

import java.util.List;

public class Yacht extends PowerBoat {
    private int cargoWeight;

    public Yacht(String model, int weight, List<JetEngine> engines, int cargoWeight) {
        super(model, weight, engines);
        this.cargoWeight = weight;
    }
}
