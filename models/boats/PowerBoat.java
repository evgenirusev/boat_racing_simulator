package models.boats;

import contracts.models.BoatEngine;
import contracts.models.Race;

import java.util.Collections;
import java.util.List;

public class PowerBoat extends BaseBoat {
    private List<BoatEngine> engines;

    public PowerBoat(String model, int weight, List<BoatEngine> engines) {
        super(model, weight);
        this.engines = engines;
    }

    public List<BoatEngine> getEngines() {
        return Collections.unmodifiableList(engines);
    }

    public void setEngines(List<BoatEngine> engines) {
        this.engines = engines;
    }

    @Override
    public Double calculateRaceSpeed(Race race) {
        return ((double)(engines.get(0).getOutput()) + (double)(engines.get(1).getOutput())) - (double)getWeight()
                + ((double)(race.getOceanCurrentSpeed()) / 5);
    }
}
