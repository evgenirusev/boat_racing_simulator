package models.boats;

import contracts.models.BoatEngine;
import contracts.models.Race;

import java.util.List;

public class Yacht extends PowerBoat {
    private int cargoWeight;

    public Yacht(String model, int weight, List<BoatEngine> engines, int cargoWeight) {
        super(model, weight, engines);
        this.cargoWeight = weight;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    @Override
    public Double calculateRaceSpeed(Race race) {
        return (double)(getEngines().get(0).getOutput() - (getWeight() + getCargoWeight()) + (race.getOceanCurrentSpeed() / 2));
    }

}
