package models.boats;

import contracts.models.BoatEngine;

import java.util.List;

public class Yacht extends PowerBoat {
    private int cargoWeight;

    public Yacht(String model, int weight, List<BoatEngine> engines, int cargoWeight) {
        super(model, weight, engines);
        this.cargoWeight = weight;
    }
}
