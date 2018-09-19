package models.boats;

import contracts.models.BoatEngine;
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
}
