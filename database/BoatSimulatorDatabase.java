package database;

import contracts.database.Repository;
import contracts.models.Boat;
import contracts.models.BoatEngine;

public class BoatSimulatorDatabase {
    Repository<Boat> boats;
    Repository<BoatEngine> engines;

    public BoatSimulatorDatabase()
    {
        this.setBoats(new database.Repository());
        this.setEngines(new database.Repository());
    }

    public Repository<Boat> getBoats() {
        return this.boats;
    }

    private void setBoats(Repository<Boat> boats) {
        this.boats = boats;
    }

    public Repository<BoatEngine> getEngines() {
        return this.engines;
    }

    private void setEngines(Repository<BoatEngine> engines) {
        this.engines = engines;
    }
}
