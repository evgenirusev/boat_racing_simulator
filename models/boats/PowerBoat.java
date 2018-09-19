package models.boats;

import com.sun.javafx.UnmodifiableArrayList;
import models.JetEngine;

import java.util.Collections;
import java.util.List;

public class PowerBoat extends AbstractBoat {
    private List<JetEngine> engines;

    public PowerBoat(String model, int weight, List<JetEngine> engines) {
        super(model, weight);
        this.engines = engines;
    }

    public List<JetEngine> getEngines() {
        return Collections.unmodifiableList(engines);
    }

    public void setEngines(List<JetEngine> engines) {
        this.engines = engines;
    }
}
